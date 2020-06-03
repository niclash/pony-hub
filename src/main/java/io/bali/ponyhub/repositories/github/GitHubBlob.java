package io.bali.ponyhub.repositories.github;

public class GitHubBlob
{
    private String sha;
    private String node_id;
    private long size;
    private String url;
    private String content;
    private String encoding;

    public String getSha()
    {
        return sha;
    }

    public void setSha( String sha )
    {
        this.sha = sha;
    }

    public String getNode_id()
    {
        return node_id;
    }

    public void setNode_id( String node_id )
    {
        this.node_id = node_id;
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

    public String getContent()
    {
        return content;
    }

    public void setContent( String content )
    {
        this.content = content;
    }

    public String getEncoding()
    {
        return encoding;
    }

    public void setEncoding( String encoding )
    {
        this.encoding = encoding;
    }
}
