package io.ponylang.actor.main.rest;

import io.ponylang.actor.main.rest.stats.GitHubStats;
import io.ponylang.actor.main.rest.stats.Statistics;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public interface StatsResource
{
    @Get
    Statistics fetch();

    class StatsServerResource extends ServerResource
        implements StatsResource
    {

        @Override
        public Statistics fetch()
        {
            GitHubStats githubStats = new GitHubStats();
            Statistics statistics = new Statistics( githubStats);
            return statistics;
        }
    }
}
