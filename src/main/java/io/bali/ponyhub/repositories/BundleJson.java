package io.bali.ponyhub.repositories;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BundleJson
{
    public List<Dependency> deps;

    public static class Dependency
    {
        public String type;

        public String repo;

        public String tag;

        @JsonProperty( "local-path" )
        public String local;
    }
}
