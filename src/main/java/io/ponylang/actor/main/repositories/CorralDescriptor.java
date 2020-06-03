package io.ponylang.actor.main.repositories;

import io.ponylang.actor.main.repositories.Repository;
import java.util.ArrayList;
import java.util.List;

public class CorralDescriptor
{
    private List<Dependency> deps = new ArrayList<>();
    private Info info;

    public List<Dependency> getDeps()
    {
        return deps;
    }

    public void setDeps( List<Dependency> deps )
    {
        this.deps = deps;
    }

    public Info getInfo()
    {
        return info;
    }

    public void setInfo( Info info )
    {
        this.info = info;
    }

    public static class Dependency
    {
        private String locator;
        private String version;

        public String getLocator()
        {
            return locator;
        }

        public void setLocator( String locator )
        {
            if( locator.endsWith( ".git" ))
                locator = locator.substring( 0, locator.length() - 4 );
            this.locator = locator;
        }

        public String getVersion()
        {
            return version;
        }

        public void setVersion( String version )
        {
            this.version = version;
        }
    }

    public static class Info
    {
        private String name;

        public Info()
        {
        }

        public String getName()
        {
            return name;
        }

        public void setName( String name )
        {
            this.name = name;
        }
    }
}
