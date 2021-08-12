package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.ZonedDateTime;

@JsonIgnoreProperties
public class GitHubOrganization
{
    public String login;
    public long id;
    public String node_id;
    public String url;
    public String repos_url;
    public String events_url;
    public String hooks_url;
    public String issues_url;
    public String members_url;
    public String public_members_url;
    public String avatar_url;
    public String description;
    public String name;
    public String company;
    public String blog;
    public String location;
    public String email;
    public boolean is_verified;
    public boolean has_organization_projects;
    public boolean has_repository_projects;
    public int public_repos;
    public int public_gists;
    public int followers;
    public int following;
    public String html_url;
    public ZonedDateTime created_at;
    public ZonedDateTime updated_at;
    public String type;
}
