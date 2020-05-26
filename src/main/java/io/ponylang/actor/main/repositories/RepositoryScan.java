package io.ponylang.actor.main.repositories;

import io.ponylang.actor.main.elastic.ElasticSearchClient;
import io.ponylang.actor.main.project.CorralDescriptor;
import io.ponylang.actor.main.project.ProjectDescriptor;
import io.ponylang.actor.main.project.ProjectLoader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RepositoryScan
    implements Runnable
{
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final String locator;
    private final String version;
    private final ElasticSearchClient elastic;
    private final ProjectLoader projectLoader;

    public RepositoryScan( String locator, String version, ElasticSearchClient elastic, ProjectLoader projectLoader )
    {
        this.locator = locator;
        this.version = version;
        this.elastic = elastic;
        this.projectLoader = projectLoader;
        executor.submit( this );
    }

    @Override
    public void run()
    {
        try
        {
            Repository repository = Repository.parse( locator );
            if( repository == null )
            {
                return;
            }
            elastic.store( repository );
            if( !elastic.recentVersionExists( repository, version ) )
            {
                RepositoryHost host = repository.host();
                CorralDescriptor descriptor = host.loadCorralDescriptor( repository, version );
                if( descriptor == null )
                {
                    return;
                }
                descriptor.setRepository( repository );
                ProjectDescriptor meta = projectLoader.load( descriptor );
                elastic.store( meta );
                for( CorralDescriptor.Dependency dep : descriptor.getDeps() )
                {
                    // queue each dependency.
                    new RepositoryScan( dep.getLocator(), dep.getVersion(), elastic, projectLoader );
                }
            }
        }
        catch( Throwable e )
        {
            e.printStackTrace();
        }
    }
}
