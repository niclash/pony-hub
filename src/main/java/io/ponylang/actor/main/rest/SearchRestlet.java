package io.ponylang.actor.main.rest;

import io.ponylang.actor.main.elastic.ElasticSearchClient;
import java.io.IOException;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Status;

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
        throws IOException
    {
        String body = request.getEntityAsText();
        String result = elastic.search( "repository", body );
        System.out.println("Query:\t" + body + "\n\t" + result);
        response.setEntity( result, MediaType.APPLICATION_JSON );
        response.setStatus( Status.SUCCESS_OK );
    }
}

