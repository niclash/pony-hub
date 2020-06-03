package io.bali.ponyhub.rest;

import io.bali.ponyhub.rest.stats.GitHubStats;
import io.bali.ponyhub.rest.stats.Statistics;
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
