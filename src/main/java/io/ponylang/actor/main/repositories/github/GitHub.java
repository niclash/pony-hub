package io.ponylang.actor.main.repositories.github;

import io.ponylang.actor.main.ZonedDateTimeConverter;
import io.ponylang.actor.main.project.CorralDescriptor;
import io.ponylang.actor.main.repositories.Repository;
import io.ponylang.actor.main.repositories.RepositoryHost;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;
import org.apache.johnzon.mapper.internal.ConverterAdapter;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;

public class GitHub
    implements RepositoryHost
{
    private static final HttpClient client = HttpClient.newBuilder()
                                                       .followRedirects( HttpClient.Redirect.NORMAL )
                                                       .connectTimeout( Duration.ofSeconds( 20 ) )
                                                       .build();
    private GitHubApi api;
    private String token;
    private final Mapper mapper;

    public GitHub()
    {
        mapper = new MapperBuilder().addAdapter( ZonedDateTime.class, String.class, new ConverterAdapter<>( new ZonedDateTimeConverter() ) ).build();
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

    public GitHubOrganization loadOrganization( String orgName )
        throws IOException
    {
        String template = api.getOrganizationUrl();
        return load( template, GitHubOrganization.class, singletonMap( "org", orgName ), emptyMap() );
    }

    public GitHubRepository loadRepository( String owner, String name )
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
        try
        {
            String url = fillTemplate( urlTemplate, requiredArgs, optionalArgs );
            URI uri = URI.create( url );
            HttpRequest request = HttpRequest.newBuilder( uri )
                                             .GET()
                                             .header( "Accept", "application/json plain/text" )
                                             .header( "Accept-Language", "en-US,en;q=0.5" )
                                             .header( "Authorization", "token " + token )
                                             .build();
            HttpResponse<String> response = client.send( request, HttpResponse.BodyHandlers.ofString( StandardCharsets.UTF_8 ) );
            System.out.println( response.headers().firstValueAsLong( "X-RateLimit-Limit" ).orElse( 0 ) );
            System.out.println( response.headers().firstValueAsLong( "X-RateLimit-Remaining" ).orElse( 0 ) );
            System.out.println( LocalDateTime.ofEpochSecond( response.headers().firstValueAsLong( "X-RateLimit-Reset" ).orElse( 0 ), 0, ZoneOffset.ofHours( 8 ) ) );
            if( response.statusCode() == 200 )
            {
                return mapper.readObject( response.body(), resultType );
            }
            else
            {
                System.err.println( "Unable to retrieve " + url + ". Error " + response.statusCode() + " : " + response.body() );
                return null;
            }
        }
        catch( InterruptedException e )
        {
            throw new IOException( "I/O communication was interrupted.", e );
        }
    }

    @Override
    public CorralDescriptor loadCorralDescriptor( Repository repository, String version )
        throws IOException
    {
        String corralFileContent = loadBlob( repository, version, "corral.json" );
        if( corralFileContent == null )
        {
            return null;
        }
        System.out.println("Loading corral.json in " + repository + " @ " + version );
        return mapper.readObject( corralFileContent, CorralDescriptor.class );
    }

    @Override
    public String loadReadMe( Repository repository, String version )
        throws IOException
    {
        return loadBlob( repository, version, "README.md" );
    }

    public String loadBlob( Repository repository, String version, String... path )
        throws IOException
    {
        if( version == null )
        {
            version = repository.getDefaultBranch();
        }
        GitHubRepository repo = loadRepository( repository.getOrganization(), repository.getName() );
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
            String base64Content = blob.getContent().replace("\n", "").replace( "\\n", "" );
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
            System.getProperties().load( new FileInputStream( "/etc/pony/ponyactor/main.actor.properties" ) );
            token = System.getProperty( "github.token" );
        }
        catch( IOException e )
        {
        }
    }
}
