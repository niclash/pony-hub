package io.ponylang.actor.main.repositories.github;

import io.ponylang.actor.main.repositories.RepositoryVersion;

public class GitHubTag
    implements RepositoryVersion
{
    private String name;
    private String nodeId;
    private String zipBallUrl;
    private String tarBallUrl;
    private GitHubCommit commits;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getNodeId()
    {
        return nodeId;
    }

    public void setNodeId( String nodeId )
    {
        this.nodeId = nodeId;
    }

    public String getZipBallUrl()
    {
        return zipBallUrl;
    }

    public void setZipBallUrl( String zipBallUrl )
    {
        this.zipBallUrl = zipBallUrl;
    }

    public String getTarBallUrl()
    {
        return tarBallUrl;
    }

    public void setTarBallUrl( String tarBallUrl )
    {
        this.tarBallUrl = tarBallUrl;
    }

    public GitHubCommit getCommits()
    {
        return commits;
    }

    public void setCommits( GitHubCommit commits )
    {
        this.commits = commits;
    }

    @Override
    public String toString()
    {
        return "[" + name + "]";
    }
}
