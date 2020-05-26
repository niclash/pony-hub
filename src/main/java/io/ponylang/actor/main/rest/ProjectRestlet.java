package io.ponylang.actor.main.rest;

import io.ponylang.actor.main.elastic.ElasticSearchClient;
import io.ponylang.actor.main.project.ProjectLoader;
import io.ponylang.actor.main.repositories.RepositoryScan;
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
    private final ProjectLoader projectLoader;

    public ProjectRestlet( ElasticSearchClient elastic, ProjectLoader projectLoader )
    {
        this.elastic = elastic;
        this.projectLoader = projectLoader;
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
        String locator = "";
        String version = "";
        try
        {
            Form form = new Form();
            FormUtils.parse( form, request.getEntity(), true );
            locator = getValueOfForm( form, "locator" );
            if( locator != null )
            {
                version = getValueOfForm( form, "version" );
                String projectJson = elastic.findProjectByIdentity( locator, version );
                response.setEntity( projectJson, MediaType.APPLICATION_JSON );
                response.setStatus( Status.SUCCESS_OK );
            }
            else
            {
                response.setStatus( Status.CLIENT_ERROR_BAD_REQUEST, "Must provide 'locator' and optionally a 'version' in the form." );
            }
        }
        catch( Exception e )
        {
            e.printStackTrace();
            response.setStatus( Status.SERVER_ERROR_INTERNAL, e, "Unable to process " + locator + " @ " + version );
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
            version = getValueOfForm( form, "version" );
            queueRepositoryScan( locator, version );
        }
        catch( Exception e )
        {
            e.printStackTrace();
            response.setStatus( Status.SERVER_ERROR_INTERNAL, e, "Unable to process " + locator + " @ " + version );
        }
    }

    private void queueRepositoryScan( String locator, String version )
    {
        new RepositoryScan( locator, version, elastic, projectLoader );
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
