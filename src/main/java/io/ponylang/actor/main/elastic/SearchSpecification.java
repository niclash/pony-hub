package io.ponylang.actor.main.elastic;

public class SearchSpecification
{
    private String query;
    private String sort;
    private int from;
    private int size;

    public String getQuery()
    {
        return query;
    }

    public void setQuery( String query )
    {
        this.query = query;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort( String sort )
    {
        this.sort = sort;
    }

    public int getFrom()
    {
        return from;
    }

    public void setFrom( int from )
    {
        this.from = from;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize( int size )
    {
        this.size = size;
    }
}
