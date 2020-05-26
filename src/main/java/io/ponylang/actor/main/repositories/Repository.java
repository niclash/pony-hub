package io.ponylang.actor.main.repositories;

import io.ponylang.actor.main.repositories.github.GitHub;

public class Repository
{
    private static final RepositoryHost github = new GitHub();
    private static final RepositoryHost dummy = new RepositoryHost.DummyHost();

    private String host;
    private String organization;
    private String name;
    private String description;

    // TODO: get default branch from GitHub
    private String defaultBranch = "master";

    public static Repository parse( String locator )
    {
        String[] parts = locator.split( "/" );
        if( parts[ 0 ].equals( "github.com" ) )
        {
            String org = parts[ 1 ];
            String name = parts[ 2 ];
            if( name.endsWith( ".git" ) )
            {
                name = name.substring( 0, name.length() - 4 );
            }
            return new Repository( "github", org, name );
        }
        return null;
    }

    public static RepositoryHost findRepositoryHost( String hostType )
    {
        if( hostType.equals( "github" ) )
        {
            return github;
        }
        return null;
    }

    public Repository()
    {
    }

    public Repository( String host, String organization, String name )
    {
        this.host = host;
        this.organization = organization;
        this.name = name;
    }

    public RepositoryHost host()
    {
        if( host.equals( "github" ) )
        {
            return github;
        }
        return dummy;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost( String host )
    {
        this.host = host;
    }

    public String path()
    {
        return organization + "/" + name;
    }

    public String getOrganization()
    {
        return organization;
    }

    public void setOrganization( String organization )
    {
        this.organization = organization;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String identityOf( String version )
    {
        if( version == null )
        {
            return host + "_" + escape( organization ) + "_" + escape( name );
        }
        return host + "_" + escape( organization ) + "_" + escape( name ) + "_" + escape( version );
    }

    private String escape( String identifier )
    {
        return identifier.replace( "_", "__" );
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getDefaultBranch()
    {
        return defaultBranch;
    }

    public void setDefaultBranch( String defaultBranch )
    {
        this.defaultBranch = defaultBranch;
    }

    @Override
    public String toString()
    {
        return organization + "/" + name;
    }
}
