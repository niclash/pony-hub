package io.ponylang.actor.main.repositories;

import io.ponylang.actor.main.project.CorralDescriptor;
import java.io.IOException;

public interface RepositoryHost
{

    String loadReadMe( Repository repository, String version )
        throws IOException;

    CorralDescriptor loadCorralDescriptor( Repository repository, String version )
        throws IOException;

    String loadLicense( Repository repository, String version )
            throws IOException;

    class DummyHost
        implements RepositoryHost
    {
        @Override
        public String loadReadMe( Repository repository, String version )
            throws IOException
        {
            return null;
        }

        @Override
        public CorralDescriptor loadCorralDescriptor( Repository repository, String version )
            throws IOException
        {
            return null;
        }

        @Override
        public String loadLicense( Repository repository, String version )
            throws IOException
        {
            return null;
        }
    }
}
