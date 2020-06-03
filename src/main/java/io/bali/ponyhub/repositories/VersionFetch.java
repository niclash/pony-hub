package io.bali.ponyhub.repositories;

import io.bali.ponyhub.elastic.ElasticSearchClient;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

public class VersionFetch
    implements Runnable
{
    private static final ConcurrentHashMap<VersionFetch, Instant> recentFetches = new ConcurrentHashMap<>();
    private final Repository repository;
    private final RepositoryVersion version;
    private final ElasticSearchClient elastic;
    private final boolean force;

    public static void fetchVersion( Repository repository, RepositoryVersion version, ElasticSearchClient elastic, boolean force )
    {
        Instant fiftenMinutesAgo = Instant.now().minusSeconds( 900 );
        recentFetches.entrySet()
                     .removeIf( entry -> entry.getValue().isBefore( fiftenMinutesAgo ) );

        VersionFetch vf = new VersionFetch( repository, version, elastic, force );

        recentFetches.computeIfAbsent( vf, k -> {
            RepositoryScan.executor.submit( k );
            return Instant.now();
        } );
    }

    private VersionFetch( Repository repository, RepositoryVersion version, ElasticSearchClient elastic, boolean force )
    {
        this.repository = repository;
        this.version = version;
        this.elastic = elastic;
        this.force = force;
    }

    @Override
    public void run()
    {
        try
        {
            if( force || !elastic.versionExists( repository, version ) )
            {
                RepositoryHost host = repository.host();
                CorralDescriptor descriptor = host.loadCorralDescriptor( repository, version );
                BundleJson bundleJson = host.loadLegacyDeps( repository, version );
                String readMe = host.loadReadMe( repository, version );
                ProjectVersion projectVersion = host.newProjectVersion( repository, version, descriptor, bundleJson, readMe );
                elastic.store( projectVersion );
                if( descriptor != null )
                {
                    for( CorralDescriptor.Dependency dep : descriptor.getDeps() )
                    {
                        try
                        {
                            RepositoryIdentity repoId = IdentityResolver.parse( dep.getLocator() );
                            RepositoryScan.scanRepository( repoId, elastic, false );
                        }
                        catch( Exception e )
                        {
                            System.err.println( "Invalid Dependency reference in corral.json for " + repository + " @ " + version );
                        }
                    }
                }
                if( bundleJson != null )
                {
                    for( BundleJson.Dependency dep : bundleJson.deps )
                    {
                        try
                        {
                            RepositoryIdentity repoId = IdentityResolver.parseBundleJsonRef( dep.type, dep.repo );
                            RepositoryScan.scanRepository( repoId, elastic, false );
                        }
                        catch( Exception e )
                        {
                            System.err.println( "Invalid Dependency reference in bundle.json for " + repository + " @ " + version );
                        }
                    }
                }
            }
        }
        catch( Throwable e )
        {
            System.err.println( "Unable to read ElasticSearch" );
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

        VersionFetch that = (VersionFetch) o;
        if( !repository.equals( that.repository ) )
        {
            return false;
        }
        if( !version.equals( that.version ) )
        {
            return false;
        }
        return elastic.equals( that.elastic );
    }

    @Override
    public int hashCode()
    {
        int result = repository.hashCode();
        result = 31 * result + version.hashCode();
        result = 31 * result + elastic.hashCode();
        return result;
    }
}
