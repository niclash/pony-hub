package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true )
public class GitHubBlob
{
    public String sha;
    public String node_id;
    public long size;
    public String url;
    public String content;
    public String encoding;
}
