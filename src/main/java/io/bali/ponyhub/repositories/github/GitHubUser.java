package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.ZonedDateTime;

@JsonIgnoreProperties( ignoreUnknown = true )
public class GitHubUser
{
    public String login;
    public long id;
    public String node_id;
    public String avatar_url;
    public String gravatar_id;
    public String url;
    public String html_url;
    public String followers_url;
    public String following_url;
    public String gists_url;
    public String starred_url;
    public String subscriptions_url;
    public String organizations_url;
    public String repos_url;
    public String events_url;
    public String received_events_url;
    public String type;
    public boolean site_admin;
    public String name;
    public String company;
    public String blog;
    public String location;
    public String email;
    public boolean hireable;
    public String bio;
    public int public_repos;
    public int public_gists;
    public int followers;
    public int following;
    public ZonedDateTime created_at;
    public ZonedDateTime updatupdated_atedAt;
}
