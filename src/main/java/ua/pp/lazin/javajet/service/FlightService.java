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
 * The Flight service.
 *
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

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static FlightService getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Create flight.
     *
     * @param flight the flight
     * @return the flight
     */
    public Flight create(final Flight flight) {
        TimeZone timeZone = timeZoneManager.getTimeZone(flight.getDeparture());
        flight.setDepartureTimezone(timeZone.getID());
        Long id = flightDao.create(flight);
        flight.setId(id);
        return flight;
    }

    /**
     * Find all flights.
     *
     * @return the list of Flight
     */
    public List<Flight> findAll() {
        return flightDao.findAllOrderByDepartureTimeAsc();
    }

    /**
     * Find all flihts later then some date.
     *
     * @param date the date
     * @return the list of Flight
     */
    public List<Flight> findAllLaterThen(Date date) {
        return flightDao.findAllLaterThen(date);
    }

    /**
     * Find all before then list.
     *
     * @param date the date
     * @return the list of Flight
     */
    public List<Flight> findAllBeforeThen(Date date) {
        return flightDao.findAllBeforeThen(date);
    }

    /**
     * Find flight by id .
     *
     * @param flightId the flight id
     * @return the flight
     */
    public Flight findById(Long flightId) {
        return flightDao.findById(flightId);
    }

    /**
     * Find flight by id with crew .
     *
     * @param flightId the flight id
     * @return the flight
     */
    public Flight findByIdWithCrew(Long flightId) {
        Flight flight = flightDao.findById(flightId);
        Set<User> crew = userDao.findUsersByFlight(flight);
        flight.setCrew(crew);
        return flight;
    }

    /**
     * Find flight by id with crew and airports .
     *
     * @param flightId the flight id
     * @return the flight
     */
    public Flight findByIdWithCrewAirports(Long flightId) {
        Flight flight = flightDao.findById(flightId);
        flight.setCrew(userDao.findUsersByFlight(flight));
        flight.setDeparture(airportDao.findByCode(flight.getDeparture().getIataCode()));
        flight.setDestination(airportDao.findByCode(flight.getDestination().getIataCode()));
        return flight;
    }

    /**
     * Update flight and crew boolean.
     * Make update only if version of instance matches version in db.
     *
     * @param flight the flight
     * @return the boolean  - true if update was successful
     */
    public boolean updateFlightAndCrew(Flight flight) {
        return flightDao.updateWithCrew(flight);
    }

    /**
     * Update flight boolean.
     * Make update only if version of instance matches version in db.
     *
     * @param flight the flight
     * @return the boolean  - true if update was successful
     */
    public boolean updateFlight(Flight flight) {
        TimeZone timeZone = timeZoneManager.getTimeZone(flight.getDeparture());
        flight.setDepartureTimezone(timeZone.getID());
        int updatedRowsNumber = flightDao.update(flight);
        return updatedRowsNumber == 1;
    }

    /**
     * Gets all flights where that user was/is in crew  later then.
     *
     * @param user the user
     * @param date the date
     * @return the all users flights later then
     */
    public List<Flight> getAllUsersFlightsLaterThen(User user, Date date) {
        return flightDao.findAllByUserLaterThen(user, date);
    }

    /**
     * Method for asynchronous resolve TimeZone for flight. Write resolved TimeZone
     * value directly in db.
     *
     * @param flight for resolve its departure TimeZone
     */
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

