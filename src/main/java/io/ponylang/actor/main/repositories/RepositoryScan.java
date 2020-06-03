package io.ponylang.actor.main.repositories;

import io.ponylang.actor.main.StatisticsUtil;
import io.ponylang.actor.main.elastic.ElasticSearchClient;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.time.temporal.ChronoUnit.MILLIS;

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
