package ua.pp.lazin.javajet.util.timezone;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.entity.Airport;

import java.util.TimeZone;

/**
 * The type World time timezone retriever.
 *
 * @author Ruslan Lazin
 */
public class WorldTimeTimezoneRetriever implements TimeZoneRetriever {
    private static final Logger logger = Logger.getLogger(WorldTimeTimezoneRetriever.class);

    /**
     * Instantiates a new World time timezone retriever.
     */
    public WorldTimeTimezoneRetriever() {
    }

    /**
     *
     * @param airport the airport
     * @return TimeZone
     */
    public TimeZone getTimeZone(Airport airport) {
        throw new UnsupportedOperationException("Sorry, not yet");
    }
}
