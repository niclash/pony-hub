package io.bali.ponyhub.rest;

import io.bali.ponyhub.StatisticsUtil;
import io.bali.ponyhub.elastic.ElasticSearchClient;
import io.bali.ponyhub.repositories.IdentityResolver;
import io.bali.ponyhub.repositories.RepositoryScan;
import java.util.Arrays;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Parameter;
import org.restlet.data.Status;
import org.restlet.engine.util.FormUtils;

public class ProjectRestlet extends Restlet
{
    private final ElasticSearchClient elastic;

    public ProjectRestlet( ElasticSearchClient elastic )
    {
        this.elastic = elastic;
    }

    @Override
    public void handle( Request request, Response response )
    {
        super.handle( request, response );
        if( request.getMethod().equals( Method.POST ) )
        {
            doPost( request, response );
        }
        if( request.getMethod().equals( Method.GET ) )
        {
            doGet( request, response );
        }
    }

    private void doGet( Request request, Response response )
    {
        String projectVersion = request.getResourceRef().getRemainingPart( true );
        String[] parts = projectVersion.split( "/" );
        String host = parts[ 0 ];
        String owner = parts[ 1 ];
        String name = parts[ 2 ];
        String version = parts.length > 3 ? parts[ 3 ] : null;
        try
        {
            String projectJson = elastic.findProjectByIdentity( host, owner, name, version );
            response.setEntity( projectJson, MediaType.APPLICATION_JSON );
            response.setStatus( Status.SUCCESS_OK );
        }
        catch( Exception e )
        {
            e.printStackTrace();
            response.setStatus( Status.SERVER_ERROR_INTERNAL, e, "Unable to process " + Arrays.toString(parts) );
        }
    }

    private void doPost( Request request, Response response )
    {
        String locator = "";
        String version = "";
        try
        {
            Form form = new Form();
            FormUtils.parse( form, request.getEntity(), true );
            locator = getValueOfForm( form, "locator" );
            StatisticsUtil.registerAddProject( locator);
            queueRepositoryScan( locator );
        }
        catch( Exception e )
        {
            e.printStackTrace();
            response.setStatus( Status.SERVER_ERROR_INTERNAL, e, "Unable to process " + locator + " @ " + version );
        }
    }

    private void queueRepositoryScan( String locator )
    {
        new RepositoryScan( IdentityResolver.parse( locator ), elastic, true );
    }

    private String getValueOfForm( Form form, String key )
    {
        Parameter param = form.getFirst( key );
        if( param == null )
        {
            return null;
        }
        return param.getValue();
    }
}
