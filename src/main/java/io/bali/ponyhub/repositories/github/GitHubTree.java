package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class GitHubTree
{
    public String sha;
    public String url;
    public boolean truncated;
    public Node[] tree;

    @JsonIgnoreProperties
    public static class Node
    {
        public String path;
        public String mode;
        public String type;
        public String sha;
        public long size;
        public String url;
    }
}
