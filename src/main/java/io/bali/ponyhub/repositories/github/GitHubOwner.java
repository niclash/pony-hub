package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.bali.ponyhub.repositories.Owner;

@JsonIgnoreProperties
public class GitHubOwner
    implements Owner
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

    @Override
    public String identity()
    {
        return login;
    }
}
