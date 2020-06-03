package io.bali.ponyhub.repositories;

public class RepositoryIdentity
{
    private String host;
    private String owner;
    private String name;

    public RepositoryIdentity()
    {
    }

    public RepositoryIdentity( String host, String owner, String name )
    {
        this.host = host;
        this.owner = owner;
        this.name = name;
    }

    public RepositoryIdentity( Repository repository )
    {
        host = repository.host().identity();
    }

    public String getHost()
    {
        return host;
    }

    public void setHost( String host )
    {
        this.host = host;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner( String owner )
    {
        this.owner = owner;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String toIdentifier()
    {
        return host + "_" + IdentityResolver.escape( owner ) + "_" + IdentityResolver.escape( name );
    }

    String identityOf( RepositoryVersion version )
    {
        return toIdentifier() + "_" + version.getName();
    }

    @Override
    public String toString()
    {
        return owner + "/" + name;
    }
}
