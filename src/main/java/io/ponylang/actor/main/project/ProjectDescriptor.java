package io.ponylang.actor.main.project;

public class ProjectDescriptor
{
    private String owner;
    private String name;
    private String description;
    private CorralDescriptor descriptor;
    private String readme;

    public CorralDescriptor getDescriptor()
    {
        return descriptor;
    }

    public void setDescriptor( CorralDescriptor descriptor )
    {
        this.descriptor = descriptor;
    }

    public String getReadme()
    {
        return readme;
    }

    public void setReadme( String readme )
    {
        this.readme = readme;
    }
}
