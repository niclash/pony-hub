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
    public GitHubRepository repository;
    public GitHubTag version;
    public CorralDescriptor descriptor;
    public BundleJson bundleJson;
    public String readMe;

    public GitHubProjectVersion()
    {
    }

    public GitHubProjectVersion( Repository repository, RepositoryVersion version, CorralDescriptor descriptor, BundleJson bundleJson, String readMe )
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
    }

    @Override
    public String organization()
    {
        return repository.getOwner().login;
    }

    @Override
    public String name()
    {
        return repository.name;
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
               + escape( repository.getOwner().login ) + "_"
               + escape( repository.name ) + "_"
               + escape( version() );
    }
}
