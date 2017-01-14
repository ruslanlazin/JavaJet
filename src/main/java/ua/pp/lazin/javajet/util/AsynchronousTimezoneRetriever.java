package ua.pp.lazin.javajet.util;

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
public class AsynchronousTimezoneRetriever {
    private static final Logger logger = Logger.getLogger(AsynchronousTimezoneRetriever.class);
    private static final GeoApiContext GEO_API_CONTEXT;

    static {
        GEO_API_CONTEXT = new GeoApiContext().setQueryRateLimit(3)
                .setConnectTimeout(10, TimeUnit.SECONDS)
                .setReadTimeout(10, TimeUnit.SECONDS)
                .setWriteTimeout(10, TimeUnit.SECONDS)
                .setApiKey("AIzaSyCfRwmQzonPq3P7o9WN_X8u0RyksS8M98o");
    }

    public AsynchronousTimezoneRetriever() {
    }

    public void retrieveTimezoneWhenCall(Airport airport,
                                         PendingResult.Callback<TimeZone> timeZoneCallback) {

        LatLng coordinates = new LatLng(airport.getLatitude(), airport.getLongitude());
        TimeZoneApi.getTimeZone(GEO_API_CONTEXT, coordinates).setCallback(timeZoneCallback);
        logger.debug("Retrieving TimeZone for Airport " + airport.getName());
    }

    public String retrieveTimezone(Airport airport) {

        LatLng coordinates = new LatLng(airport.getLatitude(), airport.getLongitude());
        TimeZone timeZone = TimeZoneApi.getTimeZone(GEO_API_CONTEXT, coordinates).awaitIgnoreError();
        logger.debug("Retrieving TimeZone for Airport " + airport.getName());
        return timeZone.getID();
    }


}
