package io.bali.ponyhub.repositories.github;

public class GitHubTree
{
    public String sha;
    public String url;
    public boolean truncated;
    public Node[] tree;

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
