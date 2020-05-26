package io.ponylang.actor.main.repositories.github;

import org.apache.johnzon.mapper.JohnzonProperty;

public class GitHubApi
{
    @JohnzonProperty( "current_user_url" )
    private String currentUserUrl;

    @JohnzonProperty( "current_user_authorization_html_url" )
    private String currentUserAuthorizationsHtmlUrl;

    @JohnzonProperty( "authorizations_url" )
    private String authorizationsUrl;

    @JohnzonProperty( "code_search_url" )
    private String codeSearchUrl;

    @JohnzonProperty( "commit_search_url" )
    private String commitSearchUrl;

    @JohnzonProperty( "emails_urls" )
    private String emailsUrl;

    @JohnzonProperty( "emojis_url" )
    private String emojisUrl;

    @JohnzonProperty( "events_url" )
    private String eventsUrl;

    @JohnzonProperty( "feeds_url" )
    private String feedsUrl;

    @JohnzonProperty( "followers_url" )
    private String followersUrl;

    @JohnzonProperty( "following_url" )
    private String followingUrl;

    @JohnzonProperty( "gists_url" )
    private String gistsUrl;

    @JohnzonProperty( "hub_url" )
    private String hubUrl;

    @JohnzonProperty( "issue_search_url" )
    private String issueSearchUrl;

    @JohnzonProperty( "issues_url" )
    private String issuesUrl;

    @JohnzonProperty( "keys_url" )
    private String keysUrl;

    @JohnzonProperty( "label_search_url" )
    private String labelSearchUrl;

    @JohnzonProperty( "notifications_url" )
    private String notificationsUrl;

    @JohnzonProperty( "organization_url" )
    private String organizationUrl;

    @JohnzonProperty( "organization_repositories_url" )
    private String organizationRepositoriesUrl;

    @JohnzonProperty( "organization_teams_url" )
    private String organizationTeamsUrl;

    @JohnzonProperty( "public_gists_url" )
    private String public_gists_url;

    @JohnzonProperty( "rate_limit_url" )
    private String rateLimitUrl;

    @JohnzonProperty( "repository_url" )
    private String repositoryUrl;

    @JohnzonProperty( "repository_search_url" )
    private String repositorySearchUrl;

    @JohnzonProperty( "current_user_repositories_url" )
    private String currentUserRepositoriesUrl;

    @JohnzonProperty( "starred_url" )
    private String starredUrl;

    @JohnzonProperty( "starred_gists_url" )
    private String starredGistsUrl;

    @JohnzonProperty( "user_url" )
    private String userUrl;

    @JohnzonProperty( "user_organizations_url" )
    private String userOrganizationsUrl;

    @JohnzonProperty( "user_repositories_url" )
    private String userRepositoriesUrl;

    @JohnzonProperty( "user_search_url" )
    private String userSearchUrl;

    public String getCurrentUserUrl()
    {
        return currentUserUrl;
    }

    public void setCurrentUserUrl( String currentUserUrl )
    {
        this.currentUserUrl = currentUserUrl;
    }

    public String getCurrentUserAuthorizationsHtmlUrl()
    {
        return currentUserAuthorizationsHtmlUrl;
    }

    public void setCurrentUserAuthorizationsHtmlUrl( String currentUserAuthorizationsHtmlUrl )
    {
        this.currentUserAuthorizationsHtmlUrl = currentUserAuthorizationsHtmlUrl;
    }

    public String getAuthorizationsUrl()
    {
        return authorizationsUrl;
    }

    public void setAuthorizationsUrl( String authorizationsUrl )
    {
        this.authorizationsUrl = authorizationsUrl;
    }

    public String getCodeSearchUrl()
    {
        return codeSearchUrl;
    }

    public void setCodeSearchUrl( String codeSearchUrl )
    {
        this.codeSearchUrl = codeSearchUrl;
    }

    public String getCommitSearchUrl()
    {
        return commitSearchUrl;
    }

    public void setCommitSearchUrl( String commitSearchUrl )
    {
        this.commitSearchUrl = commitSearchUrl;
    }

    public String getEmailsUrl()
    {
        return emailsUrl;
    }

    public void setEmailsUrl( String emailsUrl )
    {
        this.emailsUrl = emailsUrl;
    }

    public String getEmojisUrl()
    {
        return emojisUrl;
    }

    public void setEmojisUrl( String emojisUrl )
    {
        this.emojisUrl = emojisUrl;
    }

    public String getEventsUrl()
    {
        return eventsUrl;
    }

    public void setEventsUrl( String eventsUrl )
    {
        this.eventsUrl = eventsUrl;
    }

    public String getFeedsUrl()
    {
        return feedsUrl;
    }

    public void setFeedsUrl( String feedsUrl )
    {
        this.feedsUrl = feedsUrl;
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

    public String getHubUrl()
    {
        return hubUrl;
    }

    public void setHubUrl( String hubUrl )
    {
        this.hubUrl = hubUrl;
    }

    public String getIssueSearchUrl()
    {
        return issueSearchUrl;
    }

    public void setIssueSearchUrl( String issueSearchUrl )
    {
        this.issueSearchUrl = issueSearchUrl;
    }

    public String getIssuesUrl()
    {
        return issuesUrl;
    }

    public void setIssuesUrl( String issuesUrl )
    {
        this.issuesUrl = issuesUrl;
    }

    public String getKeysUrl()
    {
        return keysUrl;
    }

    public void setKeysUrl( String keysUrl )
    {
        this.keysUrl = keysUrl;
    }

    public String getLabelSearchUrl()
    {
        return labelSearchUrl;
    }

    public void setLabelSearchUrl( String labelSearchUrl )
    {
        this.labelSearchUrl = labelSearchUrl;
    }

    public String getNotificationsUrl()
    {
        return notificationsUrl;
    }

    public void setNotificationsUrl( String notificationsUrl )
    {
        this.notificationsUrl = notificationsUrl;
    }

    public String getOrganizationUrl()
    {
        return organizationUrl;
    }

    public void setOrganizationUrl( String organizationUrl )
    {
        this.organizationUrl = organizationUrl;
    }

    public String getOrganizationRepositoriesUrl()
    {
        return organizationRepositoriesUrl;
    }

    public void setOrganizationRepositoriesUrl( String organizationRepositoriesUrl )
    {
        this.organizationRepositoriesUrl = organizationRepositoriesUrl;
    }

    public String getOrganizationTeamsUrl()
    {
        return organizationTeamsUrl;
    }

    public void setOrganizationTeamsUrl( String organizationTeamsUrl )
    {
        this.organizationTeamsUrl = organizationTeamsUrl;
    }

    public String getPublic_gists_url()
    {
        return public_gists_url;
    }

    public void setPublic_gists_url( String public_gists_url )
    {
        this.public_gists_url = public_gists_url;
    }

    public String getRateLimitUrl()
    {
        return rateLimitUrl;
    }

    public void setRateLimitUrl( String rateLimitUrl )
    {
        this.rateLimitUrl = rateLimitUrl;
    }

    public String getRepositoryUrl()
    {
        return repositoryUrl;
    }

    public void setRepositoryUrl( String repositoryUrl )
    {
        this.repositoryUrl = repositoryUrl;
    }

    public String getRepositorySearchUrl()
    {
        return repositorySearchUrl;
    }

    public void setRepositorySearchUrl( String repositorySearchUrl )
    {
        this.repositorySearchUrl = repositorySearchUrl;
    }

    public String getCurrentUserRepositoriesUrl()
    {
        return currentUserRepositoriesUrl;
    }

    public void setCurrentUserRepositoriesUrl( String currentUserRepositoriesUrl )
    {
        this.currentUserRepositoriesUrl = currentUserRepositoriesUrl;
    }

    public String getStarredUrl()
    {
        return starredUrl;
    }

    public void setStarredUrl( String starredUrl )
    {
        this.starredUrl = starredUrl;
    }

    public String getStarredGistsUrl()
    {
        return starredGistsUrl;
    }

    public void setStarredGistsUrl( String starredGistsUrl )
    {
        this.starredGistsUrl = starredGistsUrl;
    }

    public String getUserUrl()
    {
        return userUrl;
    }

    public void setUserUrl( String userUrl )
    {
        this.userUrl = userUrl;
    }

    public String getUserOrganizationsUrl()
    {
        return userOrganizationsUrl;
    }

    public void setUserOrganizationsUrl( String userOrganizationsUrl )
    {
        this.userOrganizationsUrl = userOrganizationsUrl;
    }

    public String getUserRepositoriesUrl()
    {
        return userRepositoriesUrl;
    }

    public void setUserRepositoriesUrl( String userRepositoriesUrl )
    {
        this.userRepositoriesUrl = userRepositoriesUrl;
    }

    public String getUserSearchUrl()
    {
        return userSearchUrl;
    }

    public void setUserSearchUrl( String userSearchUrl )
    {
        this.userSearchUrl = userSearchUrl;
    }
}
