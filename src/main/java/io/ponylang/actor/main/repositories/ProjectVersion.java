package io.ponylang.actor.main.repositories;

public interface ProjectVersion
{
    Repository repository();

    String identity();

    String organization();

    String name();

    String version();
}
