package io.ponylang.actor.main.repositories.github;

import java.time.ZonedDateTime;
import org.apache.johnzon.mapper.JohnzonProperty;

public class GitHubUser
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

        private String name;
        private String company;
        private String blog;
        private String location;
        private String email;
        private boolean hireable;
        private String bio;

        @JohnzonProperty( "public_repos" )
        private int publicRepos;

        @JohnzonProperty( "public_gists" )
        private int publicGists;

        private int followers;
        private int following;

        @JohnzonProperty( "created_at" )
        private ZonedDateTime createdAt;

        @JohnzonProperty( "updated_at" )
        private ZonedDateTime updatedAt;

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

    public boolean isSiteAdmin()
    {
        return siteAdmin;
    }

    public void setSiteAdmin( boolean siteAdmin )
    {
        this.siteAdmin = siteAdmin;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getCompany()
    {
        return company;
    }

    public void setCompany( String company )
    {
        this.company = company;
    }

    public String getBlog()
    {
        return blog;
    }

    public void setBlog( String blog )
    {
        this.blog = blog;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation( String location )
    {
        this.location = location;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public boolean isHireable()
    {
        return hireable;
    }

    public void setHireable( boolean hireable )
    {
        this.hireable = hireable;
    }

    public String getBio()
    {
        return bio;
    }

    public void setBio( String bio )
    {
        this.bio = bio;
    }

    public int getPublicRepos()
    {
        return publicRepos;
    }

    public void setPublicRepos( int publicRepos )
    {
        this.publicRepos = publicRepos;
    }

    public int getPublicGists()
    {
        return publicGists;
    }

    public void setPublicGists( int publicGists )
    {
        this.publicGists = publicGists;
    }

    public int getFollowers()
    {
        return followers;
    }

    public void setFollowers( int followers )
    {
        this.followers = followers;
    }

    public int getFollowing()
    {
        return following;
    }

    public void setFollowing( int following )
    {
        this.following = following;
    }

    public ZonedDateTime getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt( ZonedDateTime createdAt )
    {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt( ZonedDateTime updatedAt )
    {
        this.updatedAt = updatedAt;
    }
}
