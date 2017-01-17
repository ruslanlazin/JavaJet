package ua.pp.lazin.javajet.util.timezone;

import ua.pp.lazin.javajet.entity.Airport;

import java.util.TimeZone;

/**
 * The interface Time zone retriever.
 *
 * @author Ruslan Lazin
 */
public interface TimeZoneRetriever {
    /**
     * Gets time zone.
     *
     * @param airport the airport
     * @return the time zone
     */
    TimeZone getTimeZone(Airport airport);
}
