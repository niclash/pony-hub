package io.bali.ponyhub.elastic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Total
{
    private int value;
    private String relation;

    public int getValue()
    {
        return value;
    }

    public void setValue( int value )
    {
        this.value = value;
    }

    public String getRelation()
    {
        return relation;
    }

    public void setRelation( String relation )
    {
        this.relation = relation;
    }
}
