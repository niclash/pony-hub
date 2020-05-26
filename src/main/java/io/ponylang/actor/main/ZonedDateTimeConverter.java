package io.ponylang.actor.main;

import java.time.ZonedDateTime;
import org.apache.johnzon.mapper.Converter;

public class ZonedDateTimeConverter implements Converter<ZonedDateTime>
{
    @Override
    public String toString( ZonedDateTime instance )
    {
        return instance.toString();
    }

    @Override
    public ZonedDateTime fromString( String text )
    {
        return ZonedDateTime.parse( text );
    }
}
