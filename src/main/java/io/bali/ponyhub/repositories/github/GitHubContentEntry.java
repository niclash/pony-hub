package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true )
public class GitHubContentEntry
{
    public String name;
    public String path;
    public String sha;
    public long size;
    public String url;
    public String html_url;
    public String git_url;
    public String download_url;
    public String type;
    public Link[] _links;

    public class Link
    {
        public String self;
        public String git;
        public String html;
    }
}
