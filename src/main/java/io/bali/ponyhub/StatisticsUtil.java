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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class StatisticsUtil
{
    private static final ScheduledExecutorService executor;
    private static final Pattern semVerPattern = Pattern.compile( "^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?$" );

    private static long githubMaxLimit;

    private static long githubRemainingLimit = 5000;
    private static ZonedDateTime githubResetLimit;
    private static final List<ProjectVersion> recentUpdates = new CopyOnWriteArrayList<>();
    private static long orgCount;
    private static long repositoryCount;
    private static long versionCount;

    public static void reportGithubRepository( ProjectVersion version )
    {
        boolean newer = true;
        for( ProjectVersion v : recentUpdates )
        {
            if( v.repository().host().identity().equals( version.repository().host().identity() )
                && v.organization().equals( version.organization() )
                && v.name().equals( version.name() )
            )
            {
                if( isNewerVersion( version.version(), v.version() ) )
                {
                    recentUpdates.remove( v );
                } else
                {
                    newer = false;
                }
                break;
            }
        }
        if( newer && semVerPattern.matcher( version.version() ).matches() )
        {
            recentUpdates.add( version );
        }
    }

    private static boolean isNewerVersion( String thisVersion, String previousVersion )
    {
        try
        {
            if( previousVersion.equals( "main" ) )
            {
                return true;
            }
            if( previousVersion.equals( "master" ) )
            {
                return true;
            }
            if( previousVersion.equals( "develop" ) )
            {
                return true;
            }
            Matcher thisMatcher = semVerPattern.matcher( thisVersion );
            if( !thisMatcher.matches() )
            {
                return false;
            }
            int thisMajor = Integer.parseInt( thisMatcher.group( 1 ) );
            int thisMinor = Integer.parseInt( thisMatcher.group( 2 ) );
            int thisPatch = Integer.parseInt( thisMatcher.group( 3 ) );
            Matcher previousMatcher = semVerPattern.matcher( previousVersion );
            if( !previousMatcher.matches() )
            {
                // Should never happen...
                return false;
            }
            int prevMajor = Integer.parseInt( previousMatcher.group( 1 ) );
            int prevMinor = Integer.parseInt( previousMatcher.group( 2 ) );
            int prevPatch = Integer.parseInt( previousMatcher.group( 3 ) );
            if( prevMajor > thisMajor )
            {
                return false;
            }
            if( prevMajor < thisMajor )
            {
                return true;
            }
            if( prevMinor > thisMinor )
            {
                return false;
            }
            if( prevMinor < thisMinor )
            {
                return true;
            }
            if( prevPatch > thisPatch )
            {
                return false;
            }
            if( prevPatch < thisPatch )
            {
                return true;
            }
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        return true;
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
