package io.bali.ponyhub.repositories;

public interface Repository
{
    RepositoryIdentity identity();

    String getDescription();

    Owner getOwner();

    String getDefaultBranch();

    RepositoryHost host();
}
