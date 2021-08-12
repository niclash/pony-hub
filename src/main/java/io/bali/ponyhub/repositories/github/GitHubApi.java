package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true )
public class GitHubApi
{
    public String current_user_url;
    public String current_user_authorizations_html_url;
    public String authorizations_url;
    public String code_search_url;
    public String commit_search_url;
    public String emails_url;
    public String emojis_url;
    public String events_url;
    public String feeds_url;
    public String followers_url;
    public String following_url;
    public String gists_url;
    public String hub_url;
    public String issue_search_url;
    public String issues_url;
    public String keys_url;
    public String label_search_url;
    public String notifications_url;
    public String organization_url;
    public String organization_repositories_url;
    public String organization_teams_url;
    public String public_gists_url;
    public String rate_limit_url;
    public String repository_url;
    public String repository_search_url;
    public String current_user_repositories_url;
    public String starred_url;
    public String starred_gists_url;
    public String user_url;
    public String user_organizations_url;
    public String user_repositories_url;
    public String user_search_url;
}
