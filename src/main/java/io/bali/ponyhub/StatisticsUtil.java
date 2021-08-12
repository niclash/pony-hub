package io.bali.ponyhub;

import io.bali.ponyhub.repositories.ProjectVersion;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class StatisticsUtil
{
    private static final ScheduledExecutorService executor;

    private static long githubMaxLimit;

    private static long githubRemainingLimit = 5000;
    private static ZonedDateTime githubResetLimit;
    private static final List<ProjectVersion> recentUpdates = new CopyOnWriteArrayList<>();
    private static long orgCount;
    private static long repositoryCount;
    private static long versionCount;

    public static void reportGithubRepository( ProjectVersion version )
    {
        for( ProjectVersion v : recentUpdates )
        {
            if( v.repository().host().identity().equals( version.repository().host().identity() )
                && v.organization().equals( version.organization() )
                && v.name().equals( version.name() )
            )
            {
                recentUpdates.remove( v );
                break;
            }
        }
        int ch = version.version().charAt( 0 );
        if( ( ch >= '0' && ch <= '9' ) || ( ch == 'v' && version.version().matches( "[a-zA-Z]*[0-9.]*.*" ) ) )
        {
            recentUpdates.add( version );
        }
    }

    public static void reportGithubAccess( String maxLimit, String remainingLimit, String resetLimit )
    {
        cleanup();
        StatisticsUtil.githubMaxLimit = parse( maxLimit );
        StatisticsUtil.githubRemainingLimit = parse( remainingLimit );
        Instant instant = Instant.ofEpochSecond( parse( resetLimit ) );
        StatisticsUtil.githubResetLimit = ZonedDateTime.ofInstant( instant, ZoneOffset.UTC );
    }

    private static long parse( String text )
    {
        if( text != null )
        {
            return Long.parseLong( text );
        }
        return 0;
    }

    public static long getGithubMaxLimit()
    {
        return githubMaxLimit;
    }

    public static long getGithubRemainingLimit()
    {
        return githubRemainingLimit;
    }

    public static ZonedDateTime getGithubResetLimit()
    {
        return githubResetLimit;
    }

    public static List<ProjectVersion> getRecentUpdates()
    {
        return recentUpdates;
    }

    public static long getOrgCount()
    {
        return orgCount;
    }

    public static long getRepositoryCount()
    {
        return repositoryCount;
    }

    public static long getVersionCount()
    {
        return versionCount;
    }

    private static void cleanup()
    {
        while( recentUpdates.size() > 30 )
        {
            recentUpdates.remove( 0 );
        }
    }

    static
    {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate( new Runnable()
        {
            @Override
            public void run()
            {
                orgCount = Main.elastic.findOrganizationCount();
                repositoryCount = Main.elastic.findRepositoryCount();
                versionCount = Main.elastic.findVersionCount();
            }
        }, 0, 1, TimeUnit.MINUTES );
    }

    public static void registerAddProject( String locator )
    {
        try
        {
            Path p = Paths.get( "/var/log/ponyhub/audit.log" );
            Files.writeString( p, locator + "\n", StandardCharsets.UTF_8, APPEND, CREATE );
        }
        catch( IOException e )
        {
            try
            {
                Path directory = Files.createTempDirectory( "ponyhub" );
                Path p = directory.resolve( "audit.log" );
                System.out.println( p );
                Files.writeString( p, locator + "\n", StandardCharsets.UTF_8, APPEND, CREATE );
            }
            catch( IOException ioException )
            {
                e.printStackTrace();
            }
        }
    }
}
