package io.bali.ponyhub.elastic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.bali.ponyhub.StatisticsUtil;
import io.bali.ponyhub.repositories.ProjectVersion;
import io.bali.ponyhub.repositories.Repository;
import io.bali.ponyhub.repositories.RepositoryIdentity;
import io.bali.ponyhub.repositories.RepositoryVersion;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.restlet.data.Language;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import static io.bali.ponyhub.repositories.IdentityResolver.escape;

public class ElasticSearchClient
{
    private static final String INDEX_VERSIONED_STATE = "versioned";
    private static final String INDEX_REPOSITORY = "repository";

    public final ObjectMapper mapper;

    public ElasticSearchClient()
    {
        mapper = new ObjectMapper();
        mapper.registerModule( new JavaTimeModule() );
    }

    public boolean versionExists( Repository repository, RepositoryVersion version )
    {
        RepositoryIdentity identity = repository.identity();
        String ver = "";
        if( version != null )
        {
            return false; // default version is never up-to-date
        }
        String urlName = identity.toIdentifier() + ver;
        try
        {
            ClientResource request = createDocRequest( urlName, INDEX_VERSIONED_STATE );
            request.get( MediaType.APPLICATION_JSON );
            return true;
        }
        catch( ResourceException e )
        {
            System.out.println( "Error with store: " + e.getStatus() );
            return false;
        }
    }

    public void store( ProjectVersion projectVersion )
        throws IOException
    {
        System.out.println( "Store [" + projectVersion.identity() + "]" );
        String body = mapper.writeValueAsString( projectVersion );
        String urlName = projectVersion.identity();
        StatisticsUtil.reportGithubRepository( projectVersion );
        storeItem( urlName, body, INDEX_VERSIONED_STATE );
    }

    public void store( Repository repository )
        throws IOException
    {
        System.out.println( "Store [" + repository.identity() + "]" );
        String body = mapper.writeValueAsString( repository );
        RepositoryIdentity identity = repository.identity();
        storeItem( identity.toIdentifier(), body, INDEX_REPOSITORY );
    }

    private void storeItem( String urlName, String body, String index )
    {
        ClientResource request = createDocRequest( urlName, index );
        try
        {
            request.put( new StringRepresentation( body, MediaType.APPLICATION_JSON ) );
            System.out.println( "Success: [" + request + "]" );
        }
        catch( ResourceException e )
        {
            Status response = e.getStatus();
            System.err.println( "Unable to store in ElasticSearch: " + response.getCode() + " (" + response.toString() + ") for [" + urlName + "]" );
        }
    }

    public <T> T loadDocument( String index, String docId, Class<T> type )
        throws IOException
    {
        ClientResource request = createDocRequest( docId, index );
        try
        {
            Representation representation = request.get();
            System.out.println( "Loaded document: " + docId + "  ==>  " + representation.getText() );
            return mapper.readValue( representation.getStream(), type );
        }
        catch( ResourceException e )
        {
            System.err.println( "Unable to read in ElasticSearch: " + e.getStatus() );
            return null;
        }
    }

    public String findProjectByIdentity( String host, String owner, String name, String version )
        throws IOException
    {
        String count = count( INDEX_VERSIONED_STATE );
        String id = escape( host ) + "_" + escape( owner ) + "_" + escape( name ) + "_" + escape( version );
        try
        {
            ClientResource request = createDocRequest( id, INDEX_VERSIONED_STATE );
            System.out.println( "Requesting document: " + request );
            Representation representation = request.get();
            return representation.getText();
        }
        catch( ResourceException e )
        {
            System.err.println( "Unable to retrieve " + id + ". Error " + e.getStatus() );
            return null;
        }
    }

    private ClientResource createDocRequest( String identifier, String index )
    {
        String url = String.format( "http://[::1]:9200/%s/_doc/%s", index, identifier );
        ClientResource clientResource = newClientResource( url );
        return clientResource;
    }

    private ClientResource newClientResource( String url )
    {
        System.out.println( "Create request: " + url );
        ClientResource clientResource = new ClientResource( url );
        clientResource.accept( MediaType.APPLICATION_JSON, Language.ENGLISH_US );
        return clientResource;
    }

    public SearchResult searchContentWithLucene( String luceneFormat )
        throws IOException
    {
        String query = String.format( QUERY_WITH_LUCENE, escapeQuotes( luceneFormat ) );
        String url = "http://[::1]:9200/" + INDEX_VERSIONED_STATE + "/_search";
        ClientResource request = newClientResource( url );
        Representation representation = request.post( new StringRepresentation( query, MediaType.APPLICATION_JSON ) );
        JacksonRepresentation<SearchResult> rep = new JacksonRepresentation<>( representation, SearchResult.class );
        rep.setObjectMapper( mapper );
        return rep.getObject();
    }

    private String escapeQuotes( String text )
    {
        return text.replace( "\"", "\\\"" );
    }

    public SearchResult search( String index, String queryJson )
        throws IOException
    {
        if( index == null )
        {
            index = "repository";
        }
        String query = String.format( QUERY_WITH_LUCENE, queryJson );
        String url = "http://[::1]:9200/" + index + "/_search";
        ClientResource request = newClientResource( url );
        Representation representation = request.post( new StringRepresentation( query, MediaType.APPLICATION_JSON ) );
        JacksonRepresentation<SearchResult> rep = new JacksonRepresentation<>( representation, SearchResult.class );
        rep.setObjectMapper( mapper );
        return rep.getObject();
    }

    public String searchRaw( String index, String searchSpecificationJson )
        throws IOException
    {
        if( index == null )
        {
            index = "repository";
        }
        String url = String.format( "http://[::1]:9200/%s/_search", index );
        try
        {
            ClientResource request = newClientResource( url );
            Representation representation = request.post( new StringRepresentation( searchSpecificationJson, MediaType.APPLICATION_JSON ) );
            String body = representation.getText();
            System.out.println( body );
            return body;
        }
        catch( ResourceException e )
        {
            String msg = "Unable to retrieve " + url + ". Error " + e.getStatus();
            throw new IOException( msg, e );
        }
    }

    public SearchResult findAllDocs( String index )
        throws IOException
    {
        if( index == null )
        {
            index = "repository";
        }
        String url = "http://[::1]:9200/" + index + "/_search";
        ClientResource request = newClientResource( url );
        Representation representation = request.post( new StringRepresentation( QUERY_ALL_DOCS, MediaType.APPLICATION_JSON ) );
        JacksonRepresentation<SearchResult> rep = new JacksonRepresentation<>( representation, SearchResult.class );
        rep.setObjectMapper( mapper );
        return rep.getObject();
    }

    private String count( String index )
        throws IOException
    {
        String url = String.format( "http://[::1]:9200/%s/_count", index );
        try
        {
            ClientResource request = newClientResource( url );
            Representation representation = request.get();
            String body = representation.getText();
            System.out.println( body );
            return body;
        }
        catch( ResourceException e )
        {
            if( e.getStatus().getCode() == 404 )
            {
                // Happens when the Elastic Search store is empty, before filling things in.
                // Return a fake "empty", i.e. count=0 and buckets is empty.
                return "{\"count\":0,\"aggregations\":{\"owners\":{\"doc_count_error_upper_bound\":0,\"sum_other_doc_count\":0,\"buckets\":[]}}}";
            } else
            {
                System.err.println( "Unable to retrieve " + url + ". Error " + e.getStatus() );
                throw new IOException( "I/O communication was interrupted.", e );
            }
        }
    }

    @SuppressWarnings( "rawtypes" )
    public long findOrganizationCount()
    {
        try
        {
            String searchResult = searchRaw( INDEX_REPOSITORY, QUERY_ORGCOUNT );
            Map m1 = mapper.readValue( searchResult, Map.class );
            Map m2 = (Map) m1.get( "aggregations" );
            Map m3 = (Map) m2.get( "orgCount" );
            List buckets = (List) m3.get( "buckets" );
            return buckets.size();
        }
        catch( Exception e )
        {
            if( e instanceof IOException )
            {
                Throwable cause = e.getCause();
                if( cause instanceof ResourceException )
                {
                    if( ( (ResourceException) cause ).getStatus().getCode() == 404 )
                    {
                        return 0;
                    }
                }
            }
            e.printStackTrace();
            return -1;
        }
    }

    public long findRepositoryCount()
    {
        try
        {
            String searchResult = count( INDEX_REPOSITORY );
            Map m1 = mapper.readValue( searchResult, Map.class );
            long count = ( (Number) m1.get( "count" ) ).longValue();
            return count;
        }
        catch( Exception e )
        {
            e.printStackTrace();
            return -1;
        }
    }

    public long findVersionCount()
    {
        try
        {
            String searchResult = count( INDEX_VERSIONED_STATE );
            Map m1 = mapper.readValue( searchResult, Map.class );
            long count = ( (Number) m1.get( "count" ) ).longValue();
            return count;
        }
        catch( Exception e )
        {
            e.printStackTrace();
            return -1;
        }
    }

    private static final String QUERY_ORGCOUNT = "{\n"
                                                 + "  \"size\": 0,\n"
                                                 + "  \"aggregations\": {\n"
                                                 + "    \"orgCount\": {\n"
                                                 + "      \"terms\": {\n"
                                                 + "        \"field\": \"owner.id\"\n"
                                                 + "      }\n"
                                                 + "    }\n"
                                                 + "  }\n"
                                                 + "}";

    private static final String QUERY_WITH_LUCENE = "{\n"
                                                    + "    \"query\": {\n"
                                                    + "        \"query_string\" : {\n"
                                                    + "            \"query\" : \"%s\"\n"
                                                    + "        }\n"
                                                    + "    }\n"
                                                    + "}";

    // MAX 10,000 repositories. Soon after that we need to come up with pagination of resultsets. Currently 123 repositories.
    private static final String QUERY_ALL_DOCS = "{ \n"
                                                 + "    \"size\" : 10000,\n"
                                                 + "    \"query\" : { \n"
                                                 + "        \"match_all\" : {} \n"
                                                 + "    },\n"
                                                 + "    \"stored_fields\": []\n"
                                                 + "}";
}
