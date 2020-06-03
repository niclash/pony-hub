package io.ponylang.actor.main.repositories;

public interface Repository
{
    RepositoryIdentity identity();

    String getDescription();

    Owner getOwner();

    String getDefaultBranch();

    RepositoryHost host();
}
