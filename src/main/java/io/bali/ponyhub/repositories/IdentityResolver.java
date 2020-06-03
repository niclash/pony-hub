package io.bali.ponyhub.repositories;

import io.bali.ponyhub.repositories.github.GitHub;

public class IdentityResolver
{
    public static RepositoryHost hostOf( RepositoryIdentity repoId )
    {
        if( repoId.getHost().equals( "gh" ) )
        {
            return GitHub.INSTANCE;
        }
        return null;
    }

    public static RepositoryIdentity parse( String locator )
    {
        if( locator.startsWith( "github.com" ) )
        {
            String[] parts = locator.split( "/" );
            if( parts.length != 3 )
            {
                throw new IllegalArgumentException( "Invalid locator for GitHub host" );
            }
            return new RepositoryIdentity( "gh", parts[ 1 ], parts[ 2 ] );
        }
        throw new IllegalArgumentException( "Unknown hosting system: " + locator );
    }

    public static String escape( String identifier )
    {
        return identifier.replace( "_", "__" );
    }

    public static RepositoryIdentity parseBundleJsonRef( String type, String repo )
    {
        try
        {
            if( type.equals( "github" ) )
            {
                String[] parts = repo.split( "/" );
                String owner = parts[ 0 ];
                String name = parts[ 1 ];
                return new RepositoryIdentity( GitHub.INSTANCE.identity(), owner, name );
            }
            return null;
        }
        catch( Exception e )
        {
            String msg = "Unable to parse bundle.json : " + type + " => " + repo;
            throw new IllegalArgumentException(msg, e);
        }
    }
}
