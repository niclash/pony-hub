package io.bali.ponyhub.repositories;

import java.io.IOException;
import java.util.List;

public interface RepositoryHost
{
    String loadReadMe( Repository repository, RepositoryVersion version )
        throws IOException;

    CorralDescriptor loadCorralDescriptor( Repository repository, RepositoryVersion version )
        throws IOException;

    Repository fetchRepository( RepositoryIdentity repoId )
        throws IOException;

    List<RepositoryVersion> loadVersions( Repository repo )
        throws IOException;

    ProjectVersion newProjectVersion( Repository repository, RepositoryVersion version, CorralDescriptor descriptor, BundleJson bundleJson, String readMe );

    BundleJson loadLegacyDeps( Repository repository, RepositoryVersion version )
        throws IOException;

    String identity();
}
