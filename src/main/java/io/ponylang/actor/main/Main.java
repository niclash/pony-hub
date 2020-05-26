package io.ponylang.actor.main;

import io.ponylang.actor.main.elastic.ElasticSearchClient;
import io.ponylang.actor.main.project.ProjectLoader;
import io.ponylang.actor.main.repositories.RepositoryHost;
import io.ponylang.actor.main.rest.ProjectRestlet;
import io.ponylang.actor.main.rest.SearchRestlet;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.resource.Directory;
import org.restlet.routing.VirtualHost;

public class Main
{
    private static final String ROOT_URI = "file:///var/www/html";

    public static void main( String[] args )
        throws Exception
    {
        ElasticSearchClient elastic = new ElasticSearchClient();
        ProjectLoader projectLoader = new ProjectLoader();

        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8182);
        component.getClients().add(Protocol.FILE);

        Application application = new Application() {
            @Override
            public Restlet createInboundRoot() {
                return new Directory( getContext(), ROOT_URI);
            }
        };
        VirtualHost host = component.getDefaultHost();
        host.attach( "/project", new ProjectRestlet( elastic, projectLoader ) );
        host.attach( "/search", new SearchRestlet( elastic ) );
        host.attach( application );
        component.start();
    }
}
