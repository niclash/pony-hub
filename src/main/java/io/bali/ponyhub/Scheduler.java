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
        future = executor.scheduleWithFixedDelay( new Puller( elastic ), 2, 1658, TimeUnit.MINUTES );
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
                SearchResult result = elastic.findAllDocs( "repository" );   // Find all "repository" documents.
                for( Hit hit : result.getHits().getHits() )
                {
                    String id = hit.getId();
                    System.out.println("Checking: " + id);
                    GitHubRepository repository = elastic.loadDocument( null, id, GitHubRepository.class );
                    if( repository != null )
                    {
                        RepositoryScan.scanRepository( IdentityResolver.parse( repository.url ), elastic, true );
                        Thread.sleep( 100 );        // throttle down a little bit.
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
