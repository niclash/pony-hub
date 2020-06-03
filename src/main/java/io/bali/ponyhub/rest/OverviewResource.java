package io.bali.ponyhub.rest;

import io.bali.ponyhub.StatisticsUtil;
import io.bali.ponyhub.repositories.ProjectVersion;
import io.bali.ponyhub.repositories.Repository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public interface OverviewResource
{
    @Get
    Overview fetch();

    class OverviewServerResource extends ServerResource
        implements OverviewResource
    {

        @Override
        public Overview fetch()
        {
            List<ProjectVersion> recentUpdates = StatisticsUtil.getRecentUpdates();
            List<ProjectDescriptor> updates =
                recentUpdates.stream()
                             .map( u -> new ProjectDescriptor( u.repository(), u.version() ) )
                             .collect( Collectors.toList() );
            long orgCount = StatisticsUtil.getOrgCount();
            long repositoryCount = StatisticsUtil.getRepositoryCount();
            long versionCount = StatisticsUtil.getVersionCount();
            return new Overview( updates, orgCount, repositoryCount, versionCount );
        }
    }

    class Overview
    {
        List<ProjectDescriptor> recentUpdates;
        private final long orgCount;
        private final long repositoryCount;
        private final long versionCount;

        private Overview( List<ProjectDescriptor> recentUpdates, long orgCount, long repositoryCount, long versionCount )
        {
            this.recentUpdates = new ArrayList<>( recentUpdates );
            Collections.reverse( this.recentUpdates );
            this.orgCount = orgCount;
            this.repositoryCount = repositoryCount;
            this.versionCount = versionCount;
        }

        public List<ProjectDescriptor> getRecentUpdates()
        {
            return recentUpdates;
        }

        public long getOrgCount()
        {
            return orgCount;
        }

        public long getRepositoryCount()
        {
            return repositoryCount;
        }

        public long getVersionCount()
        {
            return versionCount;
        }
    }

    class ProjectDescriptor
    {
        private final String host;
        private final String organization;
        private final String name;
        private final String version;

        public ProjectDescriptor( Repository repository, String version )
        {
            this.host = repository.host().identity();
            this.organization = repository.getOwner().identity();
            this.name = repository.identity().getName();
            this.version = version;
        }

        public String getHost()
        {
            return host;
        }

        public String getOrganization()
        {
            return organization;
        }

        public String getName()
        {
            return name;
        }

        public String getVersion()
        {
            return version;
        }
    }
}
