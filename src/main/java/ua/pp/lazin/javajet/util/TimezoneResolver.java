package ua.pp.lazin.javajet.util;

import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.TimeZoneApi;
import com.google.maps.model.LatLng;
import org.apache.log4j.Logger;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * @author Ruslan Lazin
 */
public class TimezoneResolver {
    private static final Logger logger = Logger.getLogger(TimezoneResolver.class);

    private GeoApiContext context;

    public TimezoneResolver() {
        GeoApiContext context = new GeoApiContext();
        this.context = context.setQueryRateLimit(3)
                .setConnectTimeout(10, TimeUnit.SECONDS)
                .setReadTimeout(10, TimeUnit.SECONDS)
                .setWriteTimeout(10, TimeUnit.SECONDS)
                .setApiKey("AIzaSyCfRwmQzonPq3P7o9WN_X8u0RyksS8M98o");
    }

    public void testTimezone() throws Exception {
        PendingResult.Callback<TimeZone> timeZoneCallback = new PendingResult.Callback<TimeZone>() {
            @Override
            public void onResult(TimeZone result) {
                System.out.println("uuuuuu");
                System.out.println(result.getDisplayName());
            }

            @Override
            public void onFailure(Throwable e) {

            }
        };
        TimeZoneApi.getTimeZone(context, new LatLng(39.6034810, -119.6822510)).setCallback(timeZoneCallback);

        System.out.println("vse");
        return ;
    }



}
