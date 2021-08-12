package io.bali.ponyhub.rest.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.ZonedDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubStats
{
    private long maxReqPerHour;
    private long remainingReqThisHour;
    private ZonedDateTime thisHourResetsAt;
}
