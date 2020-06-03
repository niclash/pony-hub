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

        RepositoryIdentity that = (RepositoryIdentity) o;

        if( host != null ? !host.equals( that.host ) : that.host != null )
        {
            return false;
        }
        if( owner != null ? !owner.equals( that.owner ) : that.owner != null )
        {
            return false;
        }
        return name != null ? name.equals( that.name ) : that.name == null;
    }

    @Override
    public int hashCode()
    {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + ( owner != null ? owner.hashCode() : 0 );
        result = 31 * result + ( name != null ? name.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return owner + "/" + name;
    }
}
