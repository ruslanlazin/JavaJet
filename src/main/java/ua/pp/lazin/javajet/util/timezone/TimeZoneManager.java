package ua.pp.lazin.javajet.util.timezone;

import ua.pp.lazin.javajet.entity.Airport;

import java.util.TimeZone;

/**
 * The Time zone manager implements STRATEGY pattern.
 * You can set other retriever using setTimeZoneRetriever  method
 *
 * @author Ruslan Lazin
 */
public class TimeZoneManager {

    private TimeZoneRetriever timeZoneRetriever;

    /**
     * Instantiates a new Time zone manager.
     *
     * @param timeZoneRetriever the time zone retriever
     */
    public TimeZoneManager(TimeZoneRetriever timeZoneRetriever) {
        this.timeZoneRetriever = timeZoneRetriever;
    }

    /**
     * Sets time zone retriever.
     *
     * @param timeZoneRetriever the time zone retriever
     */
    public void setTimeZoneRetriever(TimeZoneRetriever timeZoneRetriever) {
        this.timeZoneRetriever = timeZoneRetriever;
    }

    /**
     * Gets time zone.
     *
     * @param airport the airport
     * @return the time zone
     */
    public TimeZone getTimeZone(Airport airport) {
        return timeZoneRetriever.getTimeZone(airport);
    }
}

