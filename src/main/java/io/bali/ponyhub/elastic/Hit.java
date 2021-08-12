package io.bali.ponyhub.elastic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.bali.ponyhub.repositories.ProjectVersion;
import io.bali.ponyhub.repositories.github.GitHubProjectVersion;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hit
{
    private String index;
    private String type;
    private String id;
    private float score;

    // TODO: To be able to support not only GitHub projects, we need an extra level of indirection to handle deserialization. Or maybe some tricks with Jackson creators...
    private GitHubProjectVersion source;

    @JsonProperty( "_index")
    public String getIndex()
    {
        return index;
    }

    @JsonProperty( "_index")
    public void setIndex( String index )
    {
        this.index = index;
    }

    @JsonProperty( "_type")
    public String getType()
    {
        return type;
    }

    @JsonProperty( "_type")
    public void setType( String type )
    {
        this.type = type;
    }

    @JsonProperty( "_id")
    public String getId()
    {
        return id;
    }

    @JsonProperty( "_id")
    public void setId( String id )
    {
        this.id = id;
    }

    @JsonProperty( "_score")
    public float getScore()
    {
        return score;
    }

    @JsonProperty( "_score")
    public void setScore( float score )
    {
        this.score = score;
    }

    @JsonProperty( "_source")
    public GitHubProjectVersion getSource()
    {
        return source;
    }

    @JsonProperty( "_source")
    public void setSource( GitHubProjectVersion source )
    {
        this.source = source;
    }
}
