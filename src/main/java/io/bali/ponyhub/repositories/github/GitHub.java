package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.bali.ponyhub.StatisticsUtil;
import io.bali.ponyhub.repositories.BundleJson;
import io.bali.ponyhub.repositories.CorralDescriptor;
import io.bali.ponyhub.repositories.ProjectVersion;
import io.bali.ponyhub.repositories.Repository;
import io.bali.ponyhub.repositories.RepositoryHost;
import io.bali.ponyhub.repositories.RepositoryIdentity;
import io.bali.ponyhub.repositories.RepositoryVersion;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.restlet.Response;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Header;
import org.restlet.data.Language;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.util.Series;

import static java.time.temporal.ChronoUnit.MILLIS;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;

public class GitHub
    implements RepositoryHost
{
    public static final RepositoryHost INSTANCE = new GitHub();

    private GitHubApi api;
    private String token;
    private final ObjectMapper mapper;

    private GitHub()
    {
        mapper = new ObjectMapper();
        mapper.registerModule( new JavaTimeModule() );
        try
        {
            initCredentials();
            api = load( "https://api.github.com", GitHubApi.class, emptyMap(), emptyMap() );
        }
        catch( Exception e )
        {
            System.err.println( "Fatal Error. Can't reach GitHub's API" );
            e.printStackTrace();
            System.exit( 1 );
        }
    }

    @Override
    public ProjectVersion newProjectVersion( Repository repository, RepositoryVersion version, CorralDescriptor descriptor, BundleJson bundleJson, String readMe, String license )
    {
        return new GitHubProjectVersion( repository, version, descriptor, bundleJson, readMe, license );
    }

    public GitHubOrganization loadOrganization( String orgName )
        throws IOException
    {
        String template = api.getOrganizationUrl();
        return load( template, GitHubOrganization.class, singletonMap( "org", orgName ), emptyMap() );
    }

    private GitHubRepository loadRepository( String owner, String name )
        throws IOException
    {
        String template = api.getRepositoryUrl();
        return load( template, GitHubRepository.class, args( "owner", owner, "repo", name ), emptyMap() );
    }

    private Map<String, String> args( String... parts )
    {
        if( parts.length % 2 != 0 )
        {
            throw new IllegalArgumentException( "Internal Error; Must be pair of values" );
        }
        Map<String, String> result = new HashMap<>();
        for( int i = 0; i < parts.length; i += 2 )
        {
            result.put( parts[ i ], parts[ i + 1 ] );
        }
        return result;
    }

    public <T> T load( String urlTemplate, Class<T> resultType, Map<String, String> requiredArgs, Map<String, String> optionalArgs )
        throws IOException
    {
        throttle();
        String url = fillTemplate( urlTemplate, requiredArgs, optionalArgs );
        ClientResource clientResource = new ClientResource( url );
        clientResource.accept( MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, Language.ENGLISH_US );
        ChallengeResponse c = new ChallengeResponse( new ChallengeScheme( "", "" ) );
        c.setRawValue( "token " + token );
        clientResource.setChallengeResponse( c );
        clientResource.setRetryAttempts( 2 );
        clientResource.setRetryDelay( 3000 );
        clientResource.setRetryOnError( true );
        Representation representation = clientResource.get();
        Response response = clientResource.getResponse();
        Series<Header> headers = response.getHeaders();
        String maxLimit = headers.getFirstValue( "X-RateLimit-Limit" );
        String remainingLimit = headers.getFirstValue( "X-RateLimit-Remaining" );
        String resetLimit = headers.getFirstValue( "X-RateLimit-Reset" );
        StatisticsUtil.reportGithubAccess( maxLimit, remainingLimit, resetLimit );
        // We had this before, how?
        return mapper.readValue( representation.getStream(), resultType );
    }

    @Override
    public CorralDescriptor loadCorralDescriptor( Repository repository, RepositoryVersion version )
        throws IOException
    {
        String corralFileContent = loadBlob( repository, version.getName(), "corral.json" );
        if( corralFileContent == null )
        {
            return null;
        }
        System.out.println( "Loading corral.json in " + repository + " @ " + version );
        return mapper.readValue( corralFileContent, CorralDescriptor.class );
    }

    @Override
    public String loadReadMe( Repository repository, RepositoryVersion version )
        throws IOException
    {
        return loadBlob( repository, version.getName(), "README.md" );
    }

    @Override
    public String loadLicense( Repository repository, RepositoryVersion version )
        throws IOException
    {
        String license = loadBlob( repository, version.getName(), "LICENSE.txt" );
        if( license == null )
        {
            license = loadBlob( repository, version.getName(), "LICENSE.md" );
        }
        if( license == null )
        {
            license = loadBlob( repository, version.getName(), "LICENSE" );
        }
        return license;
    }

    @Override
    public Repository fetchRepository( RepositoryIdentity identity )
        throws IOException
    {
        String org = identity.getOwner();
        String name = identity.getName();
        return loadRepository( org, name );
    }

    @Override
    public List<RepositoryVersion> loadVersions( Repository repo )
        throws IOException
    {
        String template = ( (GitHubRepository) repo ).getTagsUrl();
        GitHubTag[] tags = load( template, GitHubTag[].class, emptyMap(), emptyMap() );
        List<RepositoryVersion> versionTags = Arrays.stream( tags )
                                                    .filter( v -> v.getName().matches( "[0-9]+(\\.[0-9]+)*" ) )
                                                    .collect( Collectors.toList() );
        String defaultBranch = repo.getDefaultBranch();
        versionTags.add( () -> defaultBranch );
        return versionTags;
    }

    @Override
    public BundleJson loadLegacyDeps( Repository repository, RepositoryVersion version )
        throws IOException
    {
        String bundleJsonContent = loadBlob( repository, version.getName(), "bundle.json" );
        if( bundleJsonContent == null )
        {
            return null;
        }
        System.out.println( "Loading bundle.json in " + repository + " @ " + version.getName() );
        return mapper.readValue( bundleJsonContent, BundleJson.class );
    }

    @Override
    public String identity()
    {
        return "gh";
    }

    public String loadBlob( Repository repository, String version, String... path )
        throws IOException
    {
        if( version == null )
        {
            version = repository.getDefaultBranch();
        }
        GitHubRepository repo = (GitHubRepository) repository;
        String template = repo.getTreesUrl();
        GitHubTree tree = load( template, GitHubTree.class, emptyMap(), singletonMap( "sha", version ) );
        for( String p : path )
        {
            for( GitHubTree.Node node : tree.getTree() )
            {
                if( node.getPath().equals( p ) )
                {
                    if( node.getType().equals( "tree" ) )
                    {
                        tree = load( node.getUrl(), GitHubTree.class, emptyMap(), emptyMap() );
                        break;
                    }
                    if( node.getType().equals( "blob" ) )
                    {
                        GitHubBlob blob = load( node.getUrl(), GitHubBlob.class, emptyMap(), emptyMap() );
                        return decode( blob );
                    }
                }
            }
        }
        return null;
    }

    private String decode( GitHubBlob blob )
        throws IOException
    {
        String encoding = blob.getEncoding();
        if( encoding.equals( "base64" ) )
        {
            String base64Content = blob.getContent().replace( "\n", "" ).replace( "\\n", "" );
            byte[] decode = Base64.getDecoder().decode( base64Content );
            return new String( decode );
        }
        throw new IOException( "Unknown encoding type: " + encoding );
    }

    private String fillTemplate( String urlTemplate, Map<String, String> requiredArgs, Map<String, String> optionalArgs )
    {
        String url = urlTemplate;
        for( Map.Entry<String, String> req : requiredArgs.entrySet() )
        {
            url = url.replace( "{" + req.getKey() + "}", req.getValue() );
        }
        for( Map.Entry<String, String> opt : optionalArgs.entrySet() )
        {
            url = url.replace( "{/" + opt.getKey() + "}", "/" + opt.getValue() );
        }
        while( url.contains( "{/" ) )
        {
            int start = url.indexOf( "{/" );
            int end = url.indexOf( "}", start );
            url = url.substring( 0, start ) + url.substring( end + 1 );
        }
        return url;
    }

    private void initCredentials()
    {
        try
        {
            System.getProperties().load( new FileInputStream( "/etc/ponyhub/main.actor.properties" ) );
        }
        catch( IOException e )
        {
            // ignore, happens on development system.
        }
        token = System.getProperty( "github.token" );
    }

    private void throttle()
    {
        try
        {
            long limit = StatisticsUtil.getGithubRemainingLimit();
            if( limit < 20 )
            {
                ZonedDateTime resetAt = StatisticsUtil.getGithubResetLimit();
                if( ZonedDateTime.now().isBefore( resetAt ) )
                {
                    System.out.println( "WARNING: Throttling access to GitHub, as Request Limit is approaching." );
                    Thread.sleep( ZonedDateTime.now().until( resetAt, MILLIS ) );
                }
            }
        }
        catch( InterruptedException e )
        {
            // ignore
        }
    }
}
