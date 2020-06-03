package io.ponylang.actor.main.repositories.github;

import io.ponylang.actor.main.repositories.Owner;
import org.apache.johnzon.mapper.JohnzonProperty;

public class GitHubOwner
    implements Owner
{
    private String login;
    private long id;

    @JohnzonProperty( "node_id" )
    private String nodeId;

    @JohnzonProperty( "avatar_url" )
    private String avatarUrl;

    @JohnzonProperty( "gravatar_id" )
    private String gravatarId;

    private String url;

    @JohnzonProperty( "html_url" )
    private String htmlUrl;

    @JohnzonProperty( "followers_url" )
    private String followersUrl;

    @JohnzonProperty( "following_url" )
    private String followingUrl;

    @JohnzonProperty( "gists_url" )
    private String gistsUrl;

    @JohnzonProperty( "starred_url" )
    private String starredUrl;

    @JohnzonProperty( "subscriptions_url" )
    private String subscriptionsUrl;

    @JohnzonProperty( "organizations_url" )
    private String organizationsUrl;

    @JohnzonProperty( "repos_url" )
    private String reposUrl;

    @JohnzonProperty( "events_url" )
    private String eventsUrl;

    @JohnzonProperty( "received_events_url" )
    private String receivedEventsUrl;

    private String type;

    @JohnzonProperty( "site_admin" )
    private boolean siteAdmin;

    public String getLogin()
    {
        return login;
    }

    public void setLogin( String login )
    {
        this.login = login;
    }

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public String getNodeId()
    {
        return nodeId;
    }

    public void setNodeId( String nodeId )
    {
        this.nodeId = nodeId;
    }

    public String getAvatarUrl()
    {
        return avatarUrl;
    }

    public void setAvatarUrl( String avatarUrl )
    {
        this.avatarUrl = avatarUrl;
    }

    public String getGravatarId()
    {
        return gravatarId;
    }

    public void setGravatarId( String gravatarId )
    {
        this.gravatarId = gravatarId;
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

    public String getFollowersUrl()
    {
        return followersUrl;
    }

    public void setFollowersUrl( String followersUrl )
    {
        this.followersUrl = followersUrl;
    }

    public String getFollowingUrl()
    {
        return followingUrl;
    }

    public void setFollowingUrl( String followingUrl )
    {
        this.followingUrl = followingUrl;
    }

    public String getGistsUrl()
    {
        return gistsUrl;
    }

    public void setGistsUrl( String gistsUrl )
    {
        this.gistsUrl = gistsUrl;
    }

    public String getStarredUrl()
    {
        return starredUrl;
    }

    public void setStarredUrl( String starredUrl )
    {
        this.starredUrl = starredUrl;
    }

    public String getSubscriptionsUrl()
    {
        return subscriptionsUrl;
    }

    public void setSubscriptionsUrl( String subscriptionsUrl )
    {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public String getOrganizationsUrl()
    {
        return organizationsUrl;
    }

    public void setOrganizationsUrl( String organizationsUrl )
    {
        this.organizationsUrl = organizationsUrl;
    }

    public String getReposUrl()
    {
        return reposUrl;
    }

    public void setReposUrl( String reposUrl )
    {
        this.reposUrl = reposUrl;
    }

    public String getEventsUrl()
    {
        return eventsUrl;
    }

    public void setEventsUrl( String eventsUrl )
    {
        this.eventsUrl = eventsUrl;
    }

    public String getReceivedEventsUrl()
    {
        return receivedEventsUrl;
    }

    public void setReceivedEventsUrl( String receivedEventsUrl )
    {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }

    public boolean getSiteAdmin()
    {
        return siteAdmin;
    }

    public void setSiteAdmin( boolean siteAdmin )
    {
        this.siteAdmin = siteAdmin;
    }

    @Override
    public String identity()
    {
        return getLogin();
    }
}
