package io.bali.ponyhub.rest.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
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
