package io.ponylang.actor.main.project;

import io.ponylang.actor.main.repositories.Repository;
import java.util.ArrayList;
import java.util.List;

public class CorralDescriptor
{
    private Repository repository;
    private String version;
    private List<Dependency> deps = new ArrayList<>();
    private Info info;

    public Repository getRepository()
    {
        return repository;
    }

    public void setRepository( Repository repository )
    {
        this.repository = repository;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion( String version )
    {
        this.version = version;
    }

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
    }
}
