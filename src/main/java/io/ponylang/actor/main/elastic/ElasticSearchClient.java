package io.ponylang.actor.main.elastic;

import io.ponylang.actor.main.ZonedDateTimeConverter;
import io.ponylang.actor.main.project.CorralDescriptor;
import io.ponylang.actor.main.project.ProjectDescriptor;
import io.ponylang.actor.main.repositories.Repository;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;
import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;
import org.apache.johnzon.mapper.internal.ConverterAdapter;

public class ElasticSearchClient
{
    private static final String INDEX_PROJECT = "project";
    private static final String INDEX_REPOSITORY = "repository";

    private static final HttpClient client = HttpClient.newBuilder()
                                                       .version( HttpClient.Version.HTTP_2 )
                                                       .followRedirects( HttpClient.Redirect.NORMAL )
                                                       .connectTimeout( Duration.ofSeconds( 20 ) )
                                                       .build();
    private final Mapper mapper;

    public ElasticSearchClient()
    {
        mapper = new MapperBuilder().addAdapter( ZonedDateTime.class, String.class, new ConverterAdapter<>( new ZonedDateTimeConverter() ) ).build();
    }

    public boolean recentVersionExists( Repository repository, String version )
        throws IOException
    {
        try
        {
            if( version == null )
            {
                return false; // master is never considered "recent"
            }
            String identity = repository.identityOf( version );
            HttpRequest request = createRequest( identity, INDEX_PROJECT ).build();
            HttpResponse<InputStream> response = client.send( request, HttpResponse.BodyHandlers.ofInputStream() );
            return response.statusCode() == 200;
            // TODO: Git allows repositioning of Tags, so
        }
        catch( InterruptedException e )
        {
            throw new IOException( "I/O communication was interrupted.", e );
        }
    }

    /**
     * Let's store project information.
     *
     * @param project project descriptor containing all the meta about the project, including documentation.
     */
    public void store( ProjectDescriptor project )
        throws IOException
    {
        CorralDescriptor descriptor = project.getDescriptor();
        String body = mapper.writeObjectAsString( descriptor );
        Repository repository = descriptor.getRepository();
        String identity = repository.identityOf( descriptor.getVersion() );
        storeItem( identity, body, INDEX_PROJECT );
    }

    public void store( Repository repository )
        throws IOException
    {
        String body = mapper.writeObjectAsString( repository );
        String identity = repository.identityOf( null );
        storeItem( identity, body, INDEX_REPOSITORY );
    }

    private void storeItem( String name, String body, String index )
        throws IOException
    {
        try
        {
            HttpRequest request = createRequest( name, index )
                .PUT( HttpRequest.BodyPublishers.ofString( body ) )
                .build();
            HttpResponse<InputStream> response = client.send( request, HttpResponse.BodyHandlers.ofInputStream() );
            if( response.statusCode() >= 200 && response.statusCode() < 300 )
            {
                System.out.println( "Success: [" + request.uri().toASCIIString() + "]" );
            }
            else
            {
                System.err.println( "Unable to store in ElasticSearch: " + response.statusCode() + " (" + response.toString() + ") for [" + request.uri().toASCIIString() + "]" );
            }
        }
        catch( InterruptedException e )
        {
            throw new IOException( "I/O communication was interrupted.", e );
        }
    }

    public String findProjectByIdentity( String locator, String version )
        throws IOException
    {
        try
        {
            Repository repository = Repository.parse( locator );
            if( repository == null )
            {
                return null;
            }
            if( version == null )
            {
                version = "master";
            }
            String identity = repository.identityOf( version );
            HttpRequest request = createRequest( identity, INDEX_PROJECT ).build();
            HttpResponse<String> response = client.send( request, HttpResponse.BodyHandlers.ofString( StandardCharsets.UTF_8 ) );
            if( response.statusCode() == 200 )
            {
                return response.body();
            }
            else
            {
                System.err.println( "Unable to retrieve " + locator + " @ " + version + ". Error " + response.statusCode() + " : " + response.body() );
                return null;
            }
        }
        catch( InterruptedException e )
        {
            throw new IOException( "I/O communication was interrupted.", e );
        }
    }

    private HttpRequest.Builder createRequest( String identity, String index )
    {
        String url = String.format( "http://[::1]:9200/%s/_doc/%s", index, identity );
        URI uri = URI.create( url );
        return HttpRequest.newBuilder( uri )
                          .header( "Accept", "application/json" )
                          .header( "Accept-Language", "en-US,en;q=0.5" )
                          .header( "Content-Type", "application/json;charset=utf-8" );
    }

    public String search( String index, String searchSpecificationJson )
        throws IOException
    {
        try
        {
            if( index == null )
            {
                index = "repository";
            }
            SearchSpecification spec = mapper.readObject( searchSpecificationJson, SearchSpecification.class );
            validate( spec );
            String url = String.format( "http://[::1]:9200/%s/_search", index );
            URI uri = URI.create( url );
            HttpRequest request = HttpRequest.newBuilder( uri )
                                            .POST( HttpRequest.BodyPublishers.ofString( searchSpecificationJson ) )
                                            .header( "Accept", "application/json" )
                                            .header( "Accept-Language", "en-US,en;q=0.5" )
                                            .header( "Content-Type", "application/json;charset=utf-8" )
                                            .build();
            HttpResponse<String> response = client.send( request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8) );
            if( response.statusCode() == 200 )
            {
                System.out.println(response.body());
                return response.body();
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

    private void validate( SearchSpecification spec )
    {
        // TODO: What are valid specifications??
    }
}
