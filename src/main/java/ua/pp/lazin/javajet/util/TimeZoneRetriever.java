package ua.pp.lazin.javajet.util;

import ua.pp.lazin.javajet.entity.Airport;

import java.util.TimeZone;

/**
 * @author Ruslan Lazin
 */
public interface TimeZoneRetriever {
    TimeZone getTimeZone(Airport airport);
}
