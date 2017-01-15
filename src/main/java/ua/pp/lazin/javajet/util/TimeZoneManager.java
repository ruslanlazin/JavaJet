package ua.pp.lazin.javajet.util;

import com.google.maps.PendingResult;
import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.entity.Airport;
import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.persistence.dao.FlightDao;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import java.util.TimeZone;

/**
 * @author Ruslan Lazin
 */
public class TimeZoneManager {
    private static final Logger logger = Logger.getLogger(TimeZoneManager.class);

    private TimeZoneRetriever timeZoneRetriever = new GoogleMapsTimezoneRetriever();

    public TimeZoneManager(TimeZoneRetriever timeZoneRetriever) {
        this.timeZoneRetriever = timeZoneRetriever;
    }

    public void setTimeZoneRetriever(TimeZoneRetriever timeZoneRetriever) {
        this.timeZoneRetriever = timeZoneRetriever;
    }

    public TimeZone getTimeZone(Airport departure) {
        return timeZoneRetriever.getTimeZone(departure);
    }



//
//    // TODO: 11.01.2017 move
//    private static void resolveTimezoneWhenWrite(Flight flight) {
//
//        PendingResult.Callback<TimeZone> timeZoneCallback = new PendingResult.Callback<TimeZone>() {
//            @Override
//            public void onResult(TimeZone result) {
//                logger.debug("TimeZone for Flight " + flight.getId() + ", Airport " +
//                        flight.getDeparture().getName() + " resolved. It's " +
//                        result.getID() + " Calling saving to db method");
//
//                Flight freshFlight = flightDao.findById(flight.getId());
//                freshFlight.setDepartureTimezone(result.getID());
//                int rowsUpdated = flightDao.update(freshFlight);
//                if (rowsUpdated == 1) {
//                    logger.debug("... Saved successfully");
//                } else {
//                    logger.debug("Concurrent modification of Flight " + flight.getId() + "detected");
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                logger.error("Cannot resolve TimeZone for Flight " + flight, e);
//            }
//        };
//
//        GoogleMapsTimezoneRetriever resolver = new GoogleMapsTimezoneRetriever();
//        resolver.retrieveTimezoneWhenCall(flight.getDeparture(), timeZoneCallback);
//    }


}
