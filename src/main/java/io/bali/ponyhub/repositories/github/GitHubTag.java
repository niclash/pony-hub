package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.bali.ponyhub.repositories.RepositoryVersion;

@JsonIgnoreProperties
public class GitHubTag
    implements RepositoryVersion
{
    public String name;
    public String node_id;
    public String zipball_url;
    public String tarball_url;
    public GitHubCommit commit;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "[" + name + "]";
    }
}
