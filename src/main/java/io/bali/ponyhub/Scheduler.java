package io.bali.ponyhub;

import io.bali.ponyhub.elastic.ElasticSearchClient;
import io.bali.ponyhub.elastic.Hit;
import io.bali.ponyhub.elastic.SearchResult;
import io.bali.ponyhub.repositories.IdentityResolver;
import io.bali.ponyhub.repositories.RepositoryScan;
import io.bali.ponyhub.repositories.github.GitHubRepository;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Scheduler
{
    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool( 2 );
    private ElasticSearchClient elastic;
    private ScheduledFuture<?> future;

    public Scheduler( ElasticSearchClient elastic )
    {
        this.elastic = elastic;
    }

    public void start()
    {
        future = executor.scheduleWithFixedDelay( new Puller( elastic ), 1, 1658, TimeUnit.MINUTES );
    }

    public void stop()
    {
        future.cancel( true );
    }

    private static class Puller
        implements Runnable
    {
        private ElasticSearchClient elastic;

        public Puller( ElasticSearchClient elastic )
        {
            this.elastic = elastic;
        }

        @Override
        public void run()
        {
            try
            {
                String indexName = "repository";
                SearchResult result = elastic.findAllDocs( indexName );   // Find all "repository" documents.
                for( Hit hit : result.getHits().getHits() )
                {
                    String id = hit.getId();
                    GitHubRepository repository = elastic.loadDocument( indexName, id, GitHubRepository.class );
                    System.out.println(repository);
                    if( repository != null )
                    {
                        RepositoryScan.scanRepository( IdentityResolver.parse( repository.url ), elastic, true );
                        Thread.sleep( 1000 );        // throttle down a little bit.
                    }
                }
            }
            catch( Exception e )
            {
                e.printStackTrace();
            }
        }
    }
}
