package io.bali.ponyhub.repositories;

import io.bali.ponyhub.elastic.ElasticSearchClient;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RepositoryScan
    implements Runnable
{
    static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final ConcurrentHashMap<RepositoryScan, Instant> recentFetches = new ConcurrentHashMap<>();

    private final RepositoryIdentity repoId;
    private final ElasticSearchClient elastic;
    private final boolean force;

    public static void scanRepository( RepositoryIdentity repoId, ElasticSearchClient elastic, boolean force )
    {
        Instant fiftenMinutesAgo = Instant.now().minusSeconds( 900 );
        recentFetches.entrySet()
            .removeIf( entry -> entry.getValue().isBefore( fiftenMinutesAgo ) );

        RepositoryScan scan = new RepositoryScan( repoId, elastic, force );
        recentFetches.computeIfAbsent( scan, k ->
        {
            executor.submit( k );
            return Instant.now();
        } );
    }

    private RepositoryScan( RepositoryIdentity repoId, ElasticSearchClient elastic, boolean force )
    {
        this.repoId = repoId;
        this.elastic = elastic;
        this.force = force;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println( "Scanning " + repoId );
            RepositoryHost repositoryHost = IdentityResolver.hostOf( repoId );
            if( repositoryHost != null )
            {
                Repository repo = repositoryHost.fetchRepository( repoId );
                if( repo != null )
                {
                    elastic.store( repo );
                }
                List<RepositoryVersion> versions = repo.host().loadVersions( repo );
                versions.forEach( v -> VersionFetch.fetchVersion( repo, v, elastic, force ) );
            }
        }
        catch( Throwable e )
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals( Object o )
    {
        if( this == o )
        {
            return true;
        }
        if( o == null || getClass() != o.getClass() )
        {
            return false;
        }

        RepositoryScan that = (RepositoryScan) o;

        if( !repoId.equals( that.repoId ) )
        {
            return false;
        }
        return elastic.equals( that.elastic );
    }

    @Override
    public int hashCode()
    {
        int result = repoId.hashCode();
        result = 31 * result + elastic.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "RepositoryScan{" + repoId + '}';
    }
}
