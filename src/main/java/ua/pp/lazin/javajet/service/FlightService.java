package ua.pp.lazin.javajet.service;

import com.google.maps.PendingResult;
import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.AirportDao;
import ua.pp.lazin.javajet.persistence.dao.FlightDao;
import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;
import ua.pp.lazin.javajet.util.timezone.GoogleMapsTimezoneRetriever;
import ua.pp.lazin.javajet.util.timezone.TimeZoneManager;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author Ruslan Lazin
 */
public class FlightService {
    private static final Logger logger = Logger.getLogger(FlightService.class);
    private static final FlightDao flightDao = DaoFactoryCreator.getFactory().getFlightDao();
    private static final UserDao userDao = DaoFactoryCreator.getFactory().getUserDao();
    private static final AirportDao airportDao = DaoFactoryCreator.getFactory().getAirportDao();
    private static final TimeZoneManager timeZoneManager = new TimeZoneManager(new GoogleMapsTimezoneRetriever());
    private static final FlightService INSTANCE = new FlightService();

    private FlightService() {
    }

    public static FlightService getINSTANCE() {
        return INSTANCE;
    }

    public Flight create(final Flight flight) {
        TimeZone timeZone = timeZoneManager.getTimeZone(flight.getDeparture());
        flight.setDepartureTimezone(timeZone.getID());
        Long id = flightDao.create(flight);
        flight.setId(id);
        return flight;
    }

    public List<Flight> findAll() {
        return flightDao.findAllOrderByDepartureTimeAsc();
    }

    public List<Flight> findAllLaterThen(Date date) {
        return flightDao.findAllLaterThen(date);
    }

    public List<Flight> findAllBeforeThen(Date date) {
        return flightDao.findAllBeforeThen(date);
    }

    public Flight findById(Long flightId) {
        return flightDao.findById(flightId);
    }

    public Flight findByIdWithCrew(Long flightId) {
        Flight flight = flightDao.findById(flightId);
        Set<User> crew = userDao.findUsersByFlight(flight);
        flight.setCrew(crew);
        return flight;
    }

    public Flight findByIdWithCrewAirports(Long flightId) {
        Flight flight = flightDao.findById(flightId);
        flight.setCrew(userDao.findUsersByFlight(flight));
        flight.setDeparture(airportDao.findByCode(flight.getDeparture().getIataCode()));
        flight.setDestination(airportDao.findByCode(flight.getDestination().getIataCode()));
        return flight;
    }

    public boolean updateFlightAndCrew(Flight flight) {
        return flightDao.updateWithCrew(flight);
    }

    public boolean updateFlight(Flight flight) {
        TimeZone timeZone = timeZoneManager.getTimeZone(flight.getDeparture());
        flight.setDepartureTimezone(timeZone.getID());
        int updatedRowsNumber = flightDao.update(flight);
        return updatedRowsNumber == 1;
    }

    public List<Flight> getAllUsersFlightsLaterThen(User user, Date date) {
        return flightDao.findAllByUserLaterThen(user, date);
    }

    private static void resolveTimezoneWhenWrite(Flight flight) {
        PendingResult.Callback<TimeZone> timeZoneCallback = new PendingResult.Callback<TimeZone>() {
            @Override
            public void onResult(TimeZone result) {
                logger.debug("TimeZone for Flight " + flight.getId() + ", Airport " +
                        flight.getDeparture().getName() + " resolved. It's " +
                        result.getID() + " Calling saving to db method");

                Flight freshFlight = flightDao.findById(flight.getId());
                freshFlight.setDepartureTimezone(result.getID());
                int rowsUpdated = flightDao.update(freshFlight);
                if (rowsUpdated == 1) {
                    logger.debug("... Saved successfully");
                } else {
                    logger.debug("Concurrent modification of Flight " + flight.getId() + "detected");
                }
            }

            @Override
            public void onFailure(Throwable e) {
                logger.error("Cannot resolve TimeZone for Flight " + flight, e);
            }
        };
        GoogleMapsTimezoneRetriever resolver = new GoogleMapsTimezoneRetriever();
        resolver.retrieveTimezoneWhenCall(flight.getDeparture(), timeZoneCallback);
    }
}

