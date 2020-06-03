package io.bali.ponyhub.elastic;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.bali.ponyhub.repositories.ProjectVersion;
import java.util.List;

public class Hits
{
    private Total total;
    private float maxScore;
    private List<Hit> hits;
    private ProjectVersion _source;

    public Total getTotal()
    {
        return total;
    }

    public void setTotal( Total total )
    {
        this.total = total;
    }

    @JsonProperty("max_score")
    public float getMaxScore()
    {
        return maxScore;
    }

    @JsonProperty("max_score")
    public void setMaxScore( float maxScore )
    {
        this.maxScore = maxScore;
    }

    public List<Hit> getHits()
    {
        return hits;
    }

    public void setHits( List<Hit> hits )
    {
        this.hits = hits;
    }

    @JsonProperty("_source")
    public ProjectVersion getSource()
    {
        return _source;
    }

    @JsonProperty("_source")
    public void setSource( ProjectVersion _source )
    {
        this._source = _source;
    }
}
