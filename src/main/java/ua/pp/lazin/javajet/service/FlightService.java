package ua.pp.lazin.javajet.service;

import com.google.maps.PendingResult;
import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.AirportDao;
import ua.pp.lazin.javajet.persistence.dao.FlightDao;
import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;
import ua.pp.lazin.javajet.util.AsynchronousTimezoneRetriever;
import ua.pp.lazin.javajet.util.TimeZoneManager;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author Ruslan Lazin
 */
public class FlightService {
    private final static Logger logger = Logger.getLogger(FlightService.class);
    private final static FlightDao flightDao = DaoFactoryCreator.getFactory().getFlightDao();
    private final static UserDao userDao = DaoFactoryCreator.getFactory().getUserDao();
    private final static AirportDao airportDao = DaoFactoryCreator.getFactory().getAirportDao();
    private static FlightService INSTANCE = new FlightService();

    private FlightService() {
    }

    public static FlightService getINSTANCE() {
        return INSTANCE;
    }

    public Flight create(Flight flight) {
        flight.setDepartureTimezone(TimeZoneManager.getTimeZoneForFlight(flight, "SYNC"));

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
        return flightDao.findAllBforeThen(date);
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

        flight.setDepartureTimezone(TimeZoneManager.getTimeZoneForFlight(flight, "SYNC"));
        int updatedRowsNumber = flightDao.update(flight);

//        if (flight.getDepartureTimezone() == null) {     // TODO: 08.01.2017 think

        return updatedRowsNumber == 1;

    }


    public List<Flight> getAllUsersFlightsLaterThen(User user, Date date) {
        return flightDao.findAllByUserLaterThen(user, date);
    }
}

