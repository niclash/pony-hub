package io.ponylang.actor.main.project;

import io.ponylang.actor.main.repositories.Repository;
import io.ponylang.actor.main.repositories.RepositoryHost;
import java.io.IOException;

public class ProjectLoader
{
    public ProjectDescriptor load( CorralDescriptor descriptor )
        throws IOException
    {
        Repository repository = descriptor.getRepository();
        String hostname = repository.getHost();
        RepositoryHost host = Repository.findRepositoryHost( hostname );
        if( host != null )
        {
            String readMe = host.loadReadMe( repository, descriptor.getVersion() );
            ProjectDescriptor projectDescriptor = new ProjectDescriptor();
            projectDescriptor.setDescriptor( descriptor );
            projectDescriptor.setReadme( readMe );
            return projectDescriptor;
        }
        return null;
    }
}
