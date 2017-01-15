package ua.pp.lazin.javajet.util.timezone;

import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.TimeZoneApi;
import com.google.maps.model.LatLng;
import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.entity.Airport;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * @author Ruslan Lazin
 */
public class WorldTimeTimezoneRetriever implements TimeZoneRetriever {
    private static final Logger logger = Logger.getLogger(WorldTimeTimezoneRetriever.class);

    public WorldTimeTimezoneRetriever() {
    }

    public TimeZone getTimeZone(Airport airport) {
        throw new UnsupportedOperationException("Sorry, not yet");
    }
}
