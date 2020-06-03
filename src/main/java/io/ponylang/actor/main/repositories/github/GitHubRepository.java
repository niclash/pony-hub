package io.ponylang.actor.main.repositories.github;

import io.ponylang.actor.main.repositories.Repository;
import io.ponylang.actor.main.repositories.RepositoryHost;
import io.ponylang.actor.main.repositories.RepositoryIdentity;
import java.time.ZonedDateTime;
import org.apache.johnzon.mapper.JohnzonProperty;

public class GitHubRepository
    implements Repository
{
    private long id;

    @JohnzonProperty( "node_id" )
    private String nodeId;

    private String name;

    private GitHubOwner owner;

    @JohnzonProperty( "full_name" )
    private String fullName;

    @JohnzonProperty( "private" )
    private boolean priv;

    @JohnzonProperty( "html_url" )
    private String htmlUrl;
    private String description;
    private boolean fork;
    private String url;

    @JohnzonProperty( "forks_url" )
    private String forksUrl;

    @JohnzonProperty( "keys_url" )
    private String keysUrl;

    @JohnzonProperty( "collaborators_url" )
    private String collaboratorsUrl;

    @JohnzonProperty( "teams_url" )
    private String teamsUrl;

    @JohnzonProperty( "hooks_url" )
    private String hooksUrl;

    @JohnzonProperty( "issue_events_url" )
    private String issueEventsUrl;

    @JohnzonProperty( "events_url" )
    private String eventsUrl;

    @JohnzonProperty( "assignees_url" )
    private String assigneesUrl;

    @JohnzonProperty( "branches_url" )
    private String branchesUrl;

    @JohnzonProperty( "tags_url" )
    private String tagsUrl;

    @JohnzonProperty( "blobs_url" )
    private String blobs_url;

    @JohnzonProperty( "git_tags_url" )
    private String gitTagsUrl;

    @JohnzonProperty( "get_refs_url" )
    private String gitRefsUrl;

    @JohnzonProperty( "trees_url" )
    private String treesUrl;

    @JohnzonProperty( "statuses_url" )
    private String statusesUrl;

    @JohnzonProperty( "languages_url" )
    private String languagesUrl;

    @JohnzonProperty( "stargazers_url" )
    private String stargazersUrl;

    @JohnzonProperty( "contributors_url" )
    private String contributorsUrl;

    @JohnzonProperty( "subscribers_url" )
    private String subscribersUrl;

    @JohnzonProperty( "subscription_url" )
    private String subscriptionUrl;

    @JohnzonProperty( "commits_url" )
    private String commitsUrl;

    @JohnzonProperty( "git_commits_url" )
    private String gitCommitsUrl;

    @JohnzonProperty( "comments_url" )
    private String commentsUrl;

    @JohnzonProperty( "issue_comment_url" )
    private String issueCommentUrl;

    @JohnzonProperty( "contents_url" )
    private String contentsUrl;

    @JohnzonProperty( "compare_url" )
    private String compareUrl;

    @JohnzonProperty( "merges_url" )
    private String mergesUrl;

    @JohnzonProperty( "archive_url" )
    private String archiveUrl;

    @JohnzonProperty( "downloads_url" )
    private String downloadsUrl;

    @JohnzonProperty( "issues_url" )
    private String issuesUrl;

    @JohnzonProperty( "pulls_url" )
    private String pullsUrl;

    @JohnzonProperty( "milestones_url" )
    private String milestonesUrl;

    @JohnzonProperty( "notifications_url" )
    private String notificationsUrl;

    @JohnzonProperty( "labels_url" )
    private String labelsUrl;

    @JohnzonProperty( "releases_url" )
    private String releasesUrl;

    @JohnzonProperty( "deployments_url" )
    private String deploymentsUrl;

    @JohnzonProperty( "created_at" )
    private ZonedDateTime createdAt;

    @JohnzonProperty( "updated_at" )
    private ZonedDateTime updatedAt;

    @JohnzonProperty( "pushed_at" )
    private ZonedDateTime pushedAt;

    @JohnzonProperty( "git_url" )
    private String gitUrl;

    @JohnzonProperty( "ssh_url" )
    private String sshUrl;

    @JohnzonProperty( "clone_url" )
    private String cloneUrl;

    @JohnzonProperty( "svn_url" )
    private String svnUrl;

    private String homepage;
    private long size;

    @JohnzonProperty( "stargazers_count" )
    private int stargazersCount;

    @JohnzonProperty( "watchers_count" )
    private int watchersCount;

    private String language;

    @JohnzonProperty( "has_issues" )
    private boolean hasIssues;

    @JohnzonProperty( "has_projects" )
    private boolean hasProjects;

    @JohnzonProperty( "has_downloads" )
    private boolean hasDownloads;

    @JohnzonProperty( "has_wiki" )
    private boolean hasWiki;

    @JohnzonProperty( "has_pages" )
    private boolean hasPages;

    @JohnzonProperty( "forks_count" )
    private int forksCount;

    @JohnzonProperty( "mirror_url" )
    private String mirrorUrl;

    private boolean archived;
    private boolean disabled;

    @JohnzonProperty( "open_issues_count" )
    private int openIssuesCount;

    private String license;
    private int forks;

    @JohnzonProperty( "open_issues" )
    private int open_issues;

    private int watchers;

    @JohnzonProperty( "default_branch" )
    private String defaultBranch;

    @JohnzonProperty( "temp_clone_token" )
    private String tempCloneToken;

    @JohnzonProperty( "network_count" )
    private int networkCount;

    @JohnzonProperty( "subscribers_count" )
    private int subscribersCount;

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public String getNodeId()
    {
        return nodeId;
    }

    public void setNodeId( String nodeId )
    {
        this.nodeId = nodeId;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName( String fullName )
    {
        this.fullName = fullName;
    }

    public boolean isPriv()
    {
        return priv;
    }

    public void setPriv( boolean priv )
    {
        this.priv = priv;
    }

    public String getHtmlUrl()
    {
        return htmlUrl;
    }

    public void setHtmlUrl( String htmlUrl )
    {
        this.htmlUrl = htmlUrl;
    }

    @Override
    public RepositoryIdentity identity()
    {
        return new RepositoryIdentity( host().identity(), owner.getLogin(), name );
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public boolean isFork()
    {
        return fork;
    }

    public void setFork( boolean fork )
    {
        this.fork = fork;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }

    public String getForksUrl()
    {
        return forksUrl;
    }

    public void setForksUrl( String forksUrl )
    {
        this.forksUrl = forksUrl;
    }

    public String getKeysUrl()
    {
        return keysUrl;
    }

    public void setKeysUrl( String keysUrl )
    {
        this.keysUrl = keysUrl;
    }

    public String getCollaboratorsUrl()
    {
        return collaboratorsUrl;
    }

    public void setCollaboratorsUrl( String collaboratorsUrl )
    {
        this.collaboratorsUrl = collaboratorsUrl;
    }

    public String getTeamsUrl()
    {
        return teamsUrl;
    }

    public void setTeamsUrl( String teamsUrl )
    {
        this.teamsUrl = teamsUrl;
    }

    public String getHooksUrl()
    {
        return hooksUrl;
    }

    public void setHooksUrl( String hooksUrl )
    {
        this.hooksUrl = hooksUrl;
    }

    public String getIssueEventsUrl()
    {
        return issueEventsUrl;
    }

    public void setIssueEventsUrl( String issueEventsUrl )
    {
        this.issueEventsUrl = issueEventsUrl;
    }

    public String getEventsUrl()
    {
        return eventsUrl;
    }

    public void setEventsUrl( String eventsUrl )
    {
        this.eventsUrl = eventsUrl;
    }

    public String getAssigneesUrl()
    {
        return assigneesUrl;
    }

    public void setAssigneesUrl( String assigneesUrl )
    {
        this.assigneesUrl = assigneesUrl;
    }

    public String getBranchesUrl()
    {
        return branchesUrl;
    }

    public void setBranchesUrl( String branchesUrl )
    {
        this.branchesUrl = branchesUrl;
    }

    public String getTagsUrl()
    {
        return tagsUrl;
    }

    public void setTagsUrl( String tagsUrl )
    {
        this.tagsUrl = tagsUrl;
    }

    public String getBlobs_url()
    {
        return blobs_url;
    }

    public void setBlobs_url( String blobs_url )
    {
        this.blobs_url = blobs_url;
    }

    public String getGitTagsUrl()
    {
        return gitTagsUrl;
    }

    public void setGitTagsUrl( String gitTagsUrl )
    {
        this.gitTagsUrl = gitTagsUrl;
    }

    public String getGitRefsUrl()
    {
        return gitRefsUrl;
    }

    public void setGitRefsUrl( String gitRefsUrl )
    {
        this.gitRefsUrl = gitRefsUrl;
    }

    public String getTreesUrl()
    {
        return treesUrl;
    }

    public void setTreesUrl( String treesUrl )
    {
        this.treesUrl = treesUrl;
    }

    public String getStatusesUrl()
    {
        return statusesUrl;
    }

    public void setStatusesUrl( String statusesUrl )
    {
        this.statusesUrl = statusesUrl;
    }

    public String getLanguagesUrl()
    {
        return languagesUrl;
    }

    public void setLanguagesUrl( String languagesUrl )
    {
        this.languagesUrl = languagesUrl;
    }

    public String getStargazersUrl()
    {
        return stargazersUrl;
    }

    public void setStargazersUrl( String stargazersUrl )
    {
        this.stargazersUrl = stargazersUrl;
    }

    public String getContributorsUrl()
    {
        return contributorsUrl;
    }

    public void setContributorsUrl( String contributorsUrl )
    {
        this.contributorsUrl = contributorsUrl;
    }

    public String getSubscribersUrl()
    {
        return subscribersUrl;
    }

    public void setSubscribersUrl( String subscribersUrl )
    {
        this.subscribersUrl = subscribersUrl;
    }

    public String getSubscriptionUrl()
    {
        return subscriptionUrl;
    }

    public void setSubscriptionUrl( String subscriptionUrl )
    {
        this.subscriptionUrl = subscriptionUrl;
    }

    public String getCommitsUrl()
    {
        return commitsUrl;
    }

    public void setCommitsUrl( String commitsUrl )
    {
        this.commitsUrl = commitsUrl;
    }

    public String getGitCommitsUrl()
    {
        return gitCommitsUrl;
    }

    public void setGitCommitsUrl( String gitCommitsUrl )
    {
        this.gitCommitsUrl = gitCommitsUrl;
    }

    public String getCommentsUrl()
    {
        return commentsUrl;
    }

    public void setCommentsUrl( String commentsUrl )
    {
        this.commentsUrl = commentsUrl;
    }

    public String getIssueCommentUrl()
    {
        return issueCommentUrl;
    }

    public void setIssueCommentUrl( String issueCommentUrl )
    {
        this.issueCommentUrl = issueCommentUrl;
    }

    public String getContentsUrl()
    {
        return contentsUrl;
    }

    public void setContentsUrl( String contentsUrl )
    {
        this.contentsUrl = contentsUrl;
    }

    public String getCompareUrl()
    {
        return compareUrl;
    }

    public void setCompareUrl( String compareUrl )
    {
        this.compareUrl = compareUrl;
    }

    public String getMergesUrl()
    {
        return mergesUrl;
    }

    public void setMergesUrl( String mergesUrl )
    {
        this.mergesUrl = mergesUrl;
    }

    public String getArchiveUrl()
    {
        return archiveUrl;
    }

    public void setArchiveUrl( String archiveUrl )
    {
        this.archiveUrl = archiveUrl;
    }

    public String getDownloadsUrl()
    {
        return downloadsUrl;
    }

    public void setDownloadsUrl( String downloadsUrl )
    {
        this.downloadsUrl = downloadsUrl;
    }

    public String getIssuesUrl()
    {
        return issuesUrl;
    }

    public void setIssuesUrl( String issuesUrl )
    {
        this.issuesUrl = issuesUrl;
    }

    public String getPullsUrl()
    {
        return pullsUrl;
    }

    public void setPullsUrl( String pullsUrl )
    {
        this.pullsUrl = pullsUrl;
    }

    public String getMilestonesUrl()
    {
        return milestonesUrl;
    }

    public void setMilestonesUrl( String milestonesUrl )
    {
        this.milestonesUrl = milestonesUrl;
    }

    public String getNotificationsUrl()
    {
        return notificationsUrl;
    }

    public void setNotificationsUrl( String notificationsUrl )
    {
        this.notificationsUrl = notificationsUrl;
    }

    public String getLabelsUrl()
    {
        return labelsUrl;
    }

    public void setLabelsUrl( String labelsUrl )
    {
        this.labelsUrl = labelsUrl;
    }

    public String getReleasesUrl()
    {
        return releasesUrl;
    }

    public void setReleasesUrl( String releasesUrl )
    {
        this.releasesUrl = releasesUrl;
    }

    public String getDeploymentsUrl()
    {
        return deploymentsUrl;
    }

    public void setDeploymentsUrl( String deploymentsUrl )
    {
        this.deploymentsUrl = deploymentsUrl;
    }

    public ZonedDateTime getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt( ZonedDateTime createdAt )
    {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt( ZonedDateTime updatedAt )
    {
        this.updatedAt = updatedAt;
    }

    public ZonedDateTime getPushedAt()
    {
        return pushedAt;
    }

    public void setPushedAt( ZonedDateTime pushedAt )
    {
        this.pushedAt = pushedAt;
    }

    public String getGitUrl()
    {
        return gitUrl;
    }

    public void setGitUrl( String gitUrl )
    {
        this.gitUrl = gitUrl;
    }

    public String getSshUrl()
    {
        return sshUrl;
    }

    public void setSshUrl( String sshUrl )
    {
        this.sshUrl = sshUrl;
    }

    public String getCloneUrl()
    {
        return cloneUrl;
    }

    public void setCloneUrl( String cloneUrl )
    {
        this.cloneUrl = cloneUrl;
    }

    public String getSvnUrl()
    {
        return svnUrl;
    }

    public void setSvnUrl( String svnUrl )
    {
        this.svnUrl = svnUrl;
    }

    public String getHomepage()
    {
        return homepage;
    }

    public void setHomepage( String homepage )
    {
        this.homepage = homepage;
    }

    public long getSize()
    {
        return size;
    }

    public void setSize( long size )
    {
        this.size = size;
    }

    public int getStargazersCount()
    {
        return stargazersCount;
    }

    public void setStargazersCount( int stargazersCount )
    {
        this.stargazersCount = stargazersCount;
    }

    public int getWatchersCount()
    {
        return watchersCount;
    }

    public void setWatchersCount( int watchersCount )
    {
        this.watchersCount = watchersCount;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage( String language )
    {
        this.language = language;
    }

    public boolean isHasIssues()
    {
        return hasIssues;
    }

    public void setHasIssues( boolean hasIssues )
    {
        this.hasIssues = hasIssues;
    }

    public boolean isHasProjects()
    {
        return hasProjects;
    }

    public void setHasProjects( boolean hasProjects )
    {
        this.hasProjects = hasProjects;
    }

    public boolean isHasDownloads()
    {
        return hasDownloads;
    }

    public void setHasDownloads( boolean hasDownloads )
    {
        this.hasDownloads = hasDownloads;
    }

    public boolean isHasWiki()
    {
        return hasWiki;
    }

    public void setHasWiki( boolean hasWiki )
    {
        this.hasWiki = hasWiki;
    }

    public boolean isHasPages()
    {
        return hasPages;
    }

    public void setHasPages( boolean hasPages )
    {
        this.hasPages = hasPages;
    }

    public int getForksCount()
    {
        return forksCount;
    }

    public void setForksCount( int forksCount )
    {
        this.forksCount = forksCount;
    }

    public String getMirrorUrl()
    {
        return mirrorUrl;
    }

    public void setMirrorUrl( String mirrorUrl )
    {
        this.mirrorUrl = mirrorUrl;
    }

    public boolean isArchived()
    {
        return archived;
    }

    public void setArchived( boolean archived )
    {
        this.archived = archived;
    }

    public boolean isDisabled()
    {
        return disabled;
    }

    public void setDisabled( boolean disabled )
    {
        this.disabled = disabled;
    }

    public int getOpenIssuesCount()
    {
        return openIssuesCount;
    }

    public void setOpenIssuesCount( int openIssuesCount )
    {
        this.openIssuesCount = openIssuesCount;
    }

    public String getLicense()
    {
        return license;
    }

    public void setLicense( String license )
    {
        this.license = license;
    }

    public int getForks()
    {
        return forks;
    }

    public void setForks( int forks )
    {
        this.forks = forks;
    }

    public int getOpen_issues()
    {
        return open_issues;
    }

    public void setOpen_issues( int open_issues )
    {
        this.open_issues = open_issues;
    }

    public int getWatchers()
    {
        return watchers;
    }

    public void setWatchers( int watchers )
    {
        this.watchers = watchers;
    }

    public String getDefaultBranch()
    {
        return defaultBranch;
    }

    public void setDefaultBranch( String defaultBranch )
    {
        this.defaultBranch = defaultBranch;
    }

    @Override
    public RepositoryHost host()
    {
        return GitHub.INSTANCE;
    }

    public String getTempCloneToken()
    {
        return tempCloneToken;
    }

    public void setTempCloneToken( String tempCloneToken )
    {
        this.tempCloneToken = tempCloneToken;
    }

    public int getNetworkCount()
    {
        return networkCount;
    }

    public void setNetworkCount( int networkCount )
    {
        this.networkCount = networkCount;
    }

    public int getSubscribersCount()
    {
        return subscribersCount;
    }

    public void setSubscribersCount( int subscribersCount )
    {
        this.subscribersCount = subscribersCount;
    }

    public GitHubOwner getOwner()
    {
        return owner;
    }

    public void setOwner( GitHubOwner owner )
    {
        this.owner = owner;
    }

    @Override
    public String toString()
    {
        return "github://" + owner.getLogin() + "/" + name;
    }
}
