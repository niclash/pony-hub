package io.ponylang.actor.main.rest.stats;

public class Statistics
{
    private GitHubStats github;

    public Statistics( GitHubStats github )
    {
        this.github = github;
    }

    public GitHubStats getGithub()
    {
        return github;
    }
}
