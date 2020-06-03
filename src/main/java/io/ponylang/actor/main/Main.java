package io.ponylang.actor.main;

import io.ponylang.actor.main.elastic.ElasticSearchClient;
import io.ponylang.actor.main.rest.OverviewResource;
import io.ponylang.actor.main.rest.ProjectRestlet;
import io.ponylang.actor.main.rest.SearchRestlet;
import io.ponylang.actor.main.rest.StatsResource;
import java.io.File;
import java.util.logging.Level;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.resource.Directory;
import org.restlet.routing.VirtualHost;

public class Main
{
    private static String ROOT_URI;
    public static ElasticSearchClient elastic;

    public static void main( String[] args )
        throws Exception
    {
        if( args.length > 0 && args[ 0 ].equals( "dev" ) )
        {
            ROOT_URI = "file://" + new File( System.getProperty( "user.dir" ) ).getAbsolutePath() + "/src/main/webapp";
        }
        else
        {
            ROOT_URI = "file:///var/www/html";
        }
        elastic = new ElasticSearchClient();

        Component component = new Component();
        component.getServers().add( Protocol.HTTP, 8182 );
        component.getServers().get(0).setAddress( "::1" );
        component.getClients().add( Protocol.HTTP );
        component.getClients().add( Protocol.FILE );

        Application application = new Application()
        {
            @Override
            public Restlet createInboundRoot()
            {
                return new Directory( getContext(), ROOT_URI );
            }
        };
        application.getLogger().setLevel( Level.WARNING );
        VirtualHost host = component.getDefaultHost();
        host.setHostDomain( "[::1]:8182" );
        host.attach( "/p/", new ProjectRestlet( elastic ) );
        host.attach( "/s/", new SearchRestlet( elastic ) );
        host.attach( "/st/", StatsResource.StatsServerResource.class );
        host.attach( "/ov/", OverviewResource.OverviewServerResource.class );
        host.attach( application );
        component.start();
    }
}
