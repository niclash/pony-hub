package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true )
public class GitHubCommit
{
    public String sha;
    public String url;
}
