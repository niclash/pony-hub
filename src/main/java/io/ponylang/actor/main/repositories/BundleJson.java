package io.ponylang.actor.main.repositories;

import java.util.List;

public class BundleJson
{
    private List<Dependency> deps;

    public List<Dependency> getDeps()
    {
        return deps;
    }

    public void setDeps( List<Dependency> deps )
    {
        this.deps = deps;
    }

    public static class Dependency
    {
        private String type;
        private String repo;
        private String version;

        public String getType()
        {
            return type;
        }

        public void setType( String type )
        {
            this.type = type;
        }

        public String getRepo()
        {
            return repo;
        }

        public void setRepo( String repo )
        {
            if( repo.endsWith( ".git" ) )
            {
                repo = repo.substring( 0, repo.length() - 4 );
            }
            this.repo = repo;
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
}
