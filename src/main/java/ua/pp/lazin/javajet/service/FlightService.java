package ua.pp.lazin.javajet.service;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.AirportDao;
import ua.pp.lazin.javajet.persistence.dao.FlightDao;
import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;
import ua.pp.lazin.javajet.util.GoogleMapsTimezoneRetriever;
import ua.pp.lazin.javajet.util.TimeZoneManager;

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
}

