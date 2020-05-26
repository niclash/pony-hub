package io.ponylang.actor.main.repositories.github;

import java.time.ZonedDateTime;
import org.apache.johnzon.mapper.JohnzonProperty;

public class GitHubOrganization
{
    private String login;
    private long id;

    @JohnzonProperty( "node_id" )
    private String nodeId;

    private String url;

    @JohnzonProperty( "repos_url" )
    private String reposUrl;

    @JohnzonProperty( "events_url" )
    private String eventsUrl;

    @JohnzonProperty( "hooks_url" )
    private String hooksUrl;

    @JohnzonProperty( "issues_url" )
    private String issuesUrl;

    @JohnzonProperty( "members_url" )
    private String membersUrl;

    @JohnzonProperty( "public_members_url" )
    private String publicMembersUrl;

    @JohnzonProperty( "avatar_url" )
    private String avatarUrl;

    private String description;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;

    @JohnzonProperty( "is_verified" )
    private boolean isVerified;

    @JohnzonProperty( "has_organization_projects" )
    private boolean hasOrganizationProjects;

    @JohnzonProperty( "has_repository_projects" )
    private boolean hasRepositoryProjects;

    @JohnzonProperty( "public_repos" )
    private int publicRepos;

    @JohnzonProperty( "public_gists" )
    private int publicGists;

    private int followers;
    private int following;

    @JohnzonProperty( "html_url" )
    private String html_url;

    @JohnzonProperty( "created_at" )
    private ZonedDateTime createdAt;

    @JohnzonProperty( "updated_at" )
    private ZonedDateTime updatedAt;

    private String type;

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

    public String getUrl()
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
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

    public String getHooksUrl()
    {
        return hooksUrl;
    }

    public void setHooksUrl( String hooksUrl )
    {
        this.hooksUrl = hooksUrl;
    }

    public String getIssuesUrl()
    {
        return issuesUrl;
    }

    public void setIssuesUrl( String issuesUrl )
    {
        this.issuesUrl = issuesUrl;
    }

    public String getMembersUrl()
    {
        return membersUrl;
    }

    public void setMembersUrl( String membersUrl )
    {
        this.membersUrl = membersUrl;
    }

    public String getPublicMembersUrl()
    {
        return publicMembersUrl;
    }

    public void setPublicMembersUrl( String publicMembersUrl )
    {
        this.publicMembersUrl = publicMembersUrl;
    }

    public String getAvatarUrl()
    {
        return avatarUrl;
    }

    public void setAvatarUrl( String avatarUrl )
    {
        this.avatarUrl = avatarUrl;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
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

    public boolean isVerified()
    {
        return isVerified;
    }

    public void setVerified( boolean verified )
    {
        isVerified = verified;
    }

    public boolean isHasOrganizationProjects()
    {
        return hasOrganizationProjects;
    }

    public void setHasOrganizationProjects( boolean hasOrganizationProjects )
    {
        this.hasOrganizationProjects = hasOrganizationProjects;
    }

    public boolean isHasRepositoryProjects()
    {
        return hasRepositoryProjects;
    }

    public void setHasRepositoryProjects( boolean hasRepositoryProjects )
    {
        this.hasRepositoryProjects = hasRepositoryProjects;
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

    public String getHtml_url()
    {
        return html_url;
    }

    public void setHtml_url( String html_url )
    {
        this.html_url = html_url;
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

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }
}
