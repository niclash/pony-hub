package io.bali.ponyhub.repositories;

import io.bali.ponyhub.elastic.ElasticSearchClient;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RepositoryScan
    implements Runnable
{
    static final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final RepositoryIdentity repoId;
    private final ElasticSearchClient elastic;
    private final boolean force;

    public RepositoryScan( RepositoryIdentity repoId, ElasticSearchClient elastic, boolean force )
    {
        this.repoId = repoId;
        this.elastic = elastic;
        this.force = force;
        executor.submit( this );
    }

    @Override
    public void run()
    {
        try
        {
            RepositoryHost repositoryHost = IdentityResolver.hostOf( repoId );
            if( repositoryHost != null )
            {
                Repository repo = repositoryHost.fetchRepository( repoId );
                if( repo != null )
                {
                    elastic.store( repo );
                    List<RepositoryVersion> versions = repo.host().loadVersions( repo );
                    versions.forEach( v -> {
                        new VersionFetch( repo, v, elastic, force );
                    } );
                }
            }
        }
        catch( Throwable e )
        {
            e.printStackTrace();
        }
    }

}
