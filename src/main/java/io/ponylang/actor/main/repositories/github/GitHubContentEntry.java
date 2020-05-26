package io.ponylang.actor.main.repositories.github;

import org.apache.johnzon.mapper.JohnzonProperty;

public class GitHubContentEntry
{
    private String name;
    private String path;
    private String sha;
    private long size;
    private String url;

    @JohnzonProperty( "html_url" )
    private String htmlUrl;

    @JohnzonProperty( "git_url" )
    private String gitUrl;

    @JohnzonProperty( "download_url" )
    private String downloadUrl;

    private String type;

    @JohnzonProperty( "_links" )
    private Link[] links;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath( String path )
    {
        this.path = path;
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

    public String getHtmlUrl()
    {
        return htmlUrl;
    }

    public void setHtmlUrl( String htmlUrl )
    {
        this.htmlUrl = htmlUrl;
    }

    public String getGitUrl()
    {
        return gitUrl;
    }

    public void setGitUrl( String gitUrl )
    {
        this.gitUrl = gitUrl;
    }

    public String getDownloadUrl()
    {
        return downloadUrl;
    }

    public void setDownloadUrl( String downloadUrl )
    {
        this.downloadUrl = downloadUrl;
    }

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }

    public Link[] getLinks()
    {
        return links;
    }

    public void setLinks( Link[] links )
    {
        this.links = links;
    }

    public class Link
    {
        private String self;
        private String git;
        private String html;

        public String getSelf()
        {
            return self;
        }

        public void setSelf( String self )
        {
            this.self = self;
        }

        public String getGit()
        {
            return git;
        }

        public void setGit( String git )
        {
            this.git = git;
        }

        public String getHtml()
        {
            return html;
        }

        public void setHtml( String html )
        {
            this.html = html;
        }
    }
}
