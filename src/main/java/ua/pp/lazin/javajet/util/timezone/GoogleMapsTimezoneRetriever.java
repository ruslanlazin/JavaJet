package ua.pp.lazin.javajet.util.timezone;

import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.TimeZoneApi;
import com.google.maps.model.LatLng;
import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.entity.Airport;
import ua.pp.lazin.javajet.util.PropertiesLoader;

import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * The Google maps timezone retriever.
 *
 * @author Ruslan Lazin
 */
public class GoogleMapsTimezoneRetriever implements TimeZoneRetriever {
    private static final Logger logger = Logger.getLogger(GoogleMapsTimezoneRetriever.class);
    private static final GeoApiContext GEO_API_CONTEXT;

    // Loads settings from properties file on load.
    static {
        Properties properties = PropertiesLoader.loadPropertiesFromFile("GoogleApi.properties");

        GEO_API_CONTEXT = new GeoApiContext()
                .setQueryRateLimit(Integer.parseInt(properties.getProperty("query.rate.limit")))
                .setConnectTimeout(Long.parseLong(properties.getProperty("connect.timeout")), TimeUnit.SECONDS)
                .setReadTimeout(Long.parseLong(properties.getProperty("read.timeout")), TimeUnit.SECONDS)
                .setWriteTimeout(Long.parseLong(properties.getProperty("write.timeout")), TimeUnit.SECONDS)
                .setRetryTimeout(Long.parseLong(properties.getProperty("retry.timeout")), TimeUnit.SECONDS)
                .setMaxRetries(Integer.valueOf(properties.getProperty("max.retries")))
                .setApiKey(properties.getProperty("api.key"));
    }

    /**
     * Instantiates a new Google maps timezone retriever.
     */
    public GoogleMapsTimezoneRetriever() {
    }

    /**
     * Resolves TimeZone asynchronously using Airport GPS Coordinates
     * when call timeZoneCallback method.
     *
     * @param airport          the airport
     * @param timeZoneCallback the time zone callback
     */
    public void retrieveTimezoneWhenCall(Airport airport,
                                         PendingResult.Callback<TimeZone> timeZoneCallback) {

        logger.debug("Retrieving TimeZone asynchronously for Airport " + airport.getName());
        LatLng coordinates = new LatLng(airport.getLatitude(), airport.getLongitude());
        TimeZoneApi.getTimeZone(GEO_API_CONTEXT, coordinates).setCallback(timeZoneCallback);
    }

    /**
     * Resolve TimeZone synchronously using Airport GPS Coordinates
     *
     * @param airport for resolving its
     * @return Timezone
     */
    public TimeZone getTimeZone(Airport airport) {
        logger.debug("Retrieving TimeZone synchronously for Airport " + airport.getName());
        LatLng coordinates = new LatLng(airport.getLatitude(), airport.getLongitude());
        TimeZone timeZone = TimeZoneApi.getTimeZone(GEO_API_CONTEXT, coordinates).awaitIgnoreError();

        if (timeZone != null) {
            logger.debug("TimeZone for Airport " + airport.getIataCode() +
                    ", City " + airport.getCity() + " resolved. It's " + timeZone.getID());
        } else {
            logger.debug("Resolving TimeZone for AirPort " + airport.getIataCode() +
                    ", City " + airport.getCity() + " failed");
        }
        return timeZone;
    }
}
