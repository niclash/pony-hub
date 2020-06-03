package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.bali.ponyhub.repositories.Repository;
import io.bali.ponyhub.repositories.RepositoryHost;
import io.bali.ponyhub.repositories.RepositoryIdentity;
import java.time.ZonedDateTime;

public class GitHubRepository
    implements Repository
{
    public long id;
    public String node_id;
    public String name;
    public GitHubOwner owner;
    public GitHubOwner organization;
    public GitHubRepository parent;
    public GitHubRepository source;
    public String full_name;

    @JsonProperty( "private" )
    public boolean priv;
    public String html_url;
    public String description;
    public boolean fork;
    public String url;
    public String forks_url;
    public String keys_url;
    public String collaborators_url;
    public String teams_url;
    public String hooks_url;
    public String issue_events_url;
    public String events_url;
    public String assignees_url;
    public String branches_url;
    public String tags_url;
    public String blobs_url;
    public String git_tags_url;
    public String git_refs_url;
    public String trees_url;
    public String statuses_url;
    public String languages_url;
    public String stargazers_url;
    public String contributors_url;
    public String subscribers_url;
    public String subscription_url;
    public String commits_url;
    public String git_commits_url;
    public String comments_url;
    public String issue_comment_url;
    public String contents_url;
    public String compare_url;
    public String merges_url;
    public String archive_url;
    public String downloads_url;
    public String issues_url;
    public String pulls_url;
    public String milestones_url;
    public String notifications_url;
    public String labels_url;
    public String releases_url;
    public String deployments_url;
    public ZonedDateTime created_at;
    public ZonedDateTime updated_at;
    public ZonedDateTime pushed_at;
    public String git_url;
    public String ssh_url;
    public String clone_url;
    public String svn_url;
    public String homepage;
    public long size;
    public int stargazers_count;
    public int watchers_count;
    public String language;
    public boolean has_issues;
    public boolean has_projects;
    public boolean has_downloads;
    public boolean has_wiki;
    public boolean has_pages;
    public int forks_count;
    public String mirror_url;
    public boolean archived;
    public boolean disabled;
    public int open_issues_count;
    public License license;
    public int forks;
    public int open_issues;
    public int watchers;
    public String default_branch;
    public String temp_clone_token;
    public int network_count;
    public int subscribers_count;
    public Permissions permissions;

    @Override
    public RepositoryIdentity identity()
    {
        return new RepositoryIdentity( host().identity(), owner.login, name );
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    @Override
    public String getDefaultBranch()
    {
        return default_branch;
    }

    public void setDefaultBranch( String defaultBranch )
    {
        this.default_branch = defaultBranch;
    }

    @Override
    public RepositoryHost host()
    {
        return GitHub.INSTANCE;
    }

    @Override
    public GitHubOwner getOwner()
    {
        return owner;
    }

    public void setOwner( GitHubOwner owner )
    {
        this.owner = owner;
    }

    @Override
    public String toString()
    {
        return "github://" + owner.login + "/" + name;
    }
}
