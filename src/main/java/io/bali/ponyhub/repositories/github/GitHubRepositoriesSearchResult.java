package io.bali.ponyhub.repositories.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties( ignoreUnknown = true )
public class GitHubRepositoriesSearchResult
{
    public int total_count;
    public boolean incomplete_results;
    public List<GitHubRepository> items;
}
