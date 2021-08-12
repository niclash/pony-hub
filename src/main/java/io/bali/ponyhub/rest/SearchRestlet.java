package io.bali.ponyhub.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.bali.ponyhub.elastic.ElasticSearchClient;
import io.bali.ponyhub.elastic.Hit;
import io.bali.ponyhub.repositories.ProjectVersion;
import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.resource.ResourceException;

public class SearchRestlet extends Restlet
{
    private final ElasticSearchClient elastic;

    public SearchRestlet( ElasticSearchClient elastic )
    {
        this.elastic = elastic;
    }

    @Override
    public void handle( Request request, Response response )
    {
        super.handle( request, response );
        if( request.getMethod().equals( Method.POST ) )
        {
            try
            {
                doPost( request, response );
            }
            catch( IOException e )
            {
                e.printStackTrace();
                response.setStatus( Status.SERVER_ERROR_INTERNAL );
            }
        }
        if( request.getMethod().equals( Method.GET ) )
        {
            try
            {
                doGet( request, response );
            }
            catch( IOException e )
            {
                e.printStackTrace();
                response.setStatus( Status.SERVER_ERROR_INTERNAL );
            }
        }
    }

    private void doGet( Request request, Response response )
        throws IOException
    {

    }

    private void doPost( Request request, Response response )
        throws IOException, ResourceException
    {
        String body = request.getEntityAsText();
        if( body.startsWith( "free:" ) )
        {
            io.bali.ponyhub.elastic.SearchResult result = elastic.searchContentWithLucene( body.substring( 5 ) );
            List<SearchResult> results =
                result.getHits()
                      .getHits()
                      .stream()
                      .map( hit -> {
                          float score = hit.getScore();
                          ProjectVersion p = hit.getSource();

                          String host = hostOf( hit );
                          String org = p.organization();
                          String name = p.name();
                          String version = p.version();
                          String description = p.repository().getDescription();
                          String defaultBranch = p.repository().getDefaultBranch();
                          // TODO: Extract a snippet around the search term...
                          String excerpt = "";
                          return new SearchResult( host, org, name, version, description, excerpt, defaultBranch, score );
                      } )
                      .sorted( ( h1, h2 ) -> {
                          if( h1.version.equals( h1.defaultBranch ) )
                          {
                              // h1 is on primary branch
                              if( h2.version.equals( h2.defaultBranch ) )
                              {
                                  return (int) Math.signum( h1.score - h2.score );
                              }
                              return 1;
                          }
                          else
                          {
                              if( h2.version.equals( h2.defaultBranch ) )
                              {
                                  return -1;
                              }
                              return (int) Math.signum( h1.score - h2.score );
                          }
                      } )
                      .collect( Collectors.toList() );
//            System.out.println( body + "\n" + results );
            response.setEntity( elastic.mapper.writeValueAsString( results ), MediaType.APPLICATION_JSON );
            response.setStatus( Status.SUCCESS_OK );
        }
        else
        {
            response.setStatus( Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY, "Unknown query type. Entity must start with 'free:'" );
        }
    }

    private String hostOf( Hit hit )
    {
        String id = hit.getId();
        int pos = id.indexOf( '_' );
        return id.substring( 0, pos );
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SearchResult
    {
        private final String host;
        private final String organization;
        private final String name;
        private final String version;
        private final String description;
        private final float score;
        private final String defaultBranch;
        private final String excerpt;

        public SearchResult( String host, String organization, String name, String version, String description, String excerpt, String defaultBranch, float score )
        {
            this.host = host;
            this.organization = organization;
            this.name = name;
            this.version = version;
            this.description = description;
            this.excerpt = excerpt;
            this.defaultBranch = defaultBranch;
            this.score = score;
        }

        public String getHost()
        {
            return host;
        }

        public String getOrganization()
        {
            return organization;
        }

        public String getName()
        {
            return name;
        }

        public String getVersion()
        {
            return version;
        }

        public String getDescription()
        {
            return description;
        }

        public float getScore()
        {
            return score;
        }

        public String getDefaultBranch()
        {
            return defaultBranch;
        }

        public String getExcerpt()
        {
            return excerpt;
        }

        @Override
        public String toString()
        {
            return new StringJoiner( ", ", SearchResult.class.getSimpleName() + "[", "]" )
                .add( "host='" + host + "'" )
                .add( "organization='" + organization + "'" )
                .add( "name='" + name + "'" )
                .add( "version='" + version + "'" )
                .add( "description='" + description + "'" )
                .add( "score=" + score )
                .add( "defaultBranch='" + defaultBranch + "'" )
                .add( "excerpt='" + excerpt + "'" )
                .toString();
        }
    }
}

