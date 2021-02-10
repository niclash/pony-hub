package io.bali.ponyhub.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CorralDescriptor
{
    private List<Dependency> deps = new ArrayList<>();
    private Info info;
    private List<String> packages;
    private List<Map<String, Map<String, String>>> scripts;

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

    public List<String> getPackages()
    {
        return packages;
    }

    public void setPackages( List<String> packages )
    {
        this.packages = packages;
    }

    public List<Map<String, Map<String, String>>> getScripts()
    {
        return scripts;
    }

    public void setScripts( List<Map<String, Map<String, String>>> scripts )
    {
        this.scripts = scripts;
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
            if( locator.endsWith( ".git" ) )
            {
                locator = locator.substring( 0, locator.length() - 4 );
            }
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
        public String name;
        public String description;
        public String homepage;
        public String license;
        public String documentation_url;
        public String version;
    }
}
