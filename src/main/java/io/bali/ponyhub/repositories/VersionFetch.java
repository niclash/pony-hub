package io.bali.ponyhub.repositories;

import io.bali.ponyhub.elastic.ElasticSearchClient;

public class VersionFetch
    implements Runnable
{
    private final Repository repository;
    private final RepositoryVersion version;
    private final ElasticSearchClient elastic;
    private final boolean force;

    public VersionFetch( Repository repository, RepositoryVersion version, ElasticSearchClient elastic, boolean force )
    {
        this.repository = repository;
        this.version = version;
        this.elastic = elastic;
        this.force = force;
        RepositoryScan.executor.submit( this );
    }

    @Override
    public void run()
    {
        try
        {
            if( !elastic.versionExists( repository, version ) )
            {
                RepositoryHost host = repository.host();
                CorralDescriptor descriptor = host.loadCorralDescriptor( repository, version );
                BundleJson bundleJson = host.loadLegacyDeps( repository, version );
                String readMe = host.loadReadMe( repository, version );
                String license = host.loadLicense( repository, version );
                ProjectVersion projectVersion = host.newProjectVersion( repository, version, descriptor, bundleJson, readMe, license );
                elastic.store( projectVersion );
                if( descriptor != null )
                {
                    for( CorralDescriptor.Dependency dep : descriptor.getDeps() )
                    {
                        RepositoryIdentity repoId = IdentityResolver.parse( dep.getLocator() );
                        new RepositoryScan( repoId, elastic, false );
                    }
                }
                if( bundleJson != null )
                {
                    for( BundleJson.Dependency dep : bundleJson.getDeps() )
                    {
                        RepositoryIdentity repoId = IdentityResolver.parseBundleJsonRef( dep.getType(), dep.getRepo() );
                        new RepositoryScan( repoId, elastic, false );
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
}
