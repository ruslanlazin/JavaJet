package ua.pp.lazin.javajet.util.timezone;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.entity.Airport;

import java.util.TimeZone;

/**
 * @author Ruslan Lazin
 */
public class TimeZoneManager {

    private TimeZoneRetriever timeZoneRetriever;

    public TimeZoneManager(TimeZoneRetriever timeZoneRetriever) {
        this.timeZoneRetriever = timeZoneRetriever;
    }

    public void setTimeZoneRetriever(TimeZoneRetriever timeZoneRetriever) {
        this.timeZoneRetriever = timeZoneRetriever;
    }

    public TimeZone getTimeZone(Airport departure) {
        return timeZoneRetriever.getTimeZone(departure);
    }
}

