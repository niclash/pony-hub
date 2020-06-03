package io.bali.ponyhub.repositories;

import java.util.List;

public class BundleJson
{
    public List<Dependency> deps;

    public static class Dependency
    {
        public String type;
        public String repo;
        public String tag;
    }
}
