package ua.pp.lazin.javajet.util.timezone;

import ua.pp.lazin.javajet.entity.Airport;

import java.util.TimeZone;

/**
 * @author Ruslan Lazin
 */
public interface TimeZoneRetriever {
    TimeZone getTimeZone(Airport airport);
}
