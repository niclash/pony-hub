package io.bali.ponyhub.repositories.github;

import io.bali.ponyhub.repositories.BundleJson;
import io.bali.ponyhub.repositories.CorralDescriptor;
import io.bali.ponyhub.repositories.ProjectVersion;
import io.bali.ponyhub.repositories.Repository;
import io.bali.ponyhub.repositories.RepositoryVersion;

import static io.bali.ponyhub.repositories.IdentityResolver.escape;

public class GitHubProjectVersion
    implements ProjectVersion
{
    private GitHubRepository repository;
    private GitHubTag version;
    private CorralDescriptor descriptor;
    private BundleJson bundleJson;
    private String readMe;
    private String license;

    public GitHubProjectVersion()
    {
    }

    public GitHubProjectVersion( Repository repository, RepositoryVersion version, CorralDescriptor descriptor, BundleJson bundleJson, String readMe, String license )
    {
        this.repository = (GitHubRepository) repository;
        if( version.getName().equals( repository.getDefaultBranch() ) )
        {
            this.version = null;
        }
        else
        {
            this.version = (GitHubTag) version;
        }
        this.descriptor = descriptor;
        this.bundleJson = bundleJson;
        this.readMe = readMe;
        this.license = license;
    }

    public Repository getRepository()
    {
        return repository;
    }

    public void setRepository( GitHubRepository repository )
    {
        this.repository = repository;
    }

    public RepositoryVersion getVersion()
    {
        return version;
    }

    public void setVersion( GitHubTag version )
    {
        this.version = version;
    }

    public CorralDescriptor getDescriptor()
    {
        return descriptor;
    }

    public void setDescriptor( CorralDescriptor descriptor )
    {
        this.descriptor = descriptor;
    }

    public BundleJson getBundleJson()
    {
        return bundleJson;
    }

    public void setBundleJson( BundleJson bundleJson )
    {
        this.bundleJson = bundleJson;
    }

    public String getReadMe()
    {
        return readMe;
    }

    public void setReadMe( String readMe )
    {
        this.readMe = readMe;
    }

    public String getLicense()
    {
        return license;
    }

    public void setLicense( String license )
    {
        this.license = license;
    }

    @Override
    public String organization()
    {
        return repository.getOwner().getLogin();
    }

    @Override
    public String name()
    {
        return repository.getName();
    }

    @Override
    public String version()
    {
        return version == null ? repository.getDefaultBranch() : version.getName();
    }

    @Override
    public Repository repository()
    {
        return repository;
    }

    @Override
    public String identity()
    {
        return escape( repository.host().identity() ) + "_"
               + escape( repository.getOwner().getLogin() ) + "_"
               + escape( repository.getName() ) + "_"
               + escape( version() );
    }
}
