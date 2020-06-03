package io.bali.ponyhub.repositories;

// TODO: To be able to support not only GitHub projects, we need an extra level of indirection to handle deserialization
public interface ProjectVersion
{
    Repository repository();

    String identity();

    String organization();

    String name();

    String version();
}
