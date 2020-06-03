package io.bali.ponyhub.repositories.github;

public class GitHubTree
{
    private String sha;
    private String url;
    private boolean truncated;
    private Node[] tree;

    public String getSha()
    {
        return sha;
    }

    public void setSha( String sha )
    {
        this.sha = sha;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }

    public boolean isTruncated()
    {
        return truncated;
    }

    public void setTruncated( boolean truncated )
    {
        this.truncated = truncated;
    }

    public Node[] getTree()
    {
        return tree;
    }

    public void setTree( Node[] tree )
    {
        this.tree = tree;
    }

    public static class Node
    {
        private String path;
        private String mode;
        private String type;
        private String sha;
        private long size;
        private String url;

        public String getPath()
        {
            return path;
        }

        public void setPath( String path )
        {
            this.path = path;
        }

        public String getMode()
        {
            return mode;
        }

        public void setMode( String mode )
        {
            this.mode = mode;
        }

        public String getType()
        {
            return type;
        }

        public void setType( String type )
        {
            this.type = type;
        }

        public String getSha()
        {
            return sha;
        }

        public void setSha( String sha )
        {
            this.sha = sha;
        }

        public long getSize()
        {
            return size;
        }

        public void setSize( long size )
        {
            this.size = size;
        }

        public String getUrl()
        {
            return url;
        }

        public void setUrl( String url )
        {
            this.url = url;
        }
    }
}
