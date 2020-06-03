package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;

public class GitHubOrganization
{
    private String login;
    private long id;

    @JsonProperty( "node_id" )
    private String nodeId;

    private String url;

    @JsonProperty( "repos_url" )
    private String reposUrl;

    @JsonProperty( "events_url" )
    private String eventsUrl;

    @JsonProperty( "hooks_url" )
    private String hooksUrl;

    @JsonProperty( "issues_url" )
    private String issuesUrl;

    @JsonProperty( "members_url" )
    private String membersUrl;

    @JsonProperty( "public_members_url" )
    private String publicMembersUrl;

    @JsonProperty( "avatar_url" )
    private String avatarUrl;

    private String description;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;

    @JsonProperty( "is_verified" )
    private boolean isVerified;

    @JsonProperty( "has_organization_projects" )
    private boolean hasOrganizationProjects;

    @JsonProperty( "has_repository_projects" )
    private boolean hasRepositoryProjects;

    @JsonProperty( "public_repos" )
    private int publicRepos;

    @JsonProperty( "public_gists" )
    private int publicGists;

    private int followers;
    private int following;

    @JsonProperty( "html_url" )
    private String html_url;

    @JsonProperty( "created_at" )
    private ZonedDateTime createdAt;

    @JsonProperty( "updated_at" )
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
