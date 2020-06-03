package io.bali.ponyhub.elastic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResult
{
    private int took;
    private boolean timedOut;
    private Shards shards;
    private Hits hits;

    public int getTook()
    {
        return took;
    }

    public void setTook( int took )
    {
        this.took = took;
    }

    @JsonProperty("timed_out")
    public boolean getTimedOut()
    {
        return timedOut;
    }

    @JsonProperty("timed_out")
    public void setTimedOut( boolean timed_out )
    {
        this.timedOut = timed_out;
    }

    @JsonProperty("_shards")
    public Shards getShards()
    {
        return shards;
    }

    @JsonProperty("_shards")
    public void setShards( Shards _shards )
    {
        this.shards = _shards;
    }

    public Hits getHits()
    {
        return hits;
    }

    public void setHits( Hits hits )
    {
        this.hits = hits;
    }
}
