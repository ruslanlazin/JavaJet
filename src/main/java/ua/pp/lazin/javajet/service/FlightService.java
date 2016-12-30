package ua.pp.lazin.javajet.service;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.FlightDao;
import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.persistence.entity.Flight;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.persistence.factory.DaoFactory;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class FlightService {
    private final static Logger logger = Logger.getLogger(FlightService.class);
    private final static FlightDao flightDao = DaoFactoryCreator.getFactory().getFlightDao();
    private final static UserDao userDao = DaoFactoryCreator.getFactory().getUserDao();
    private static FlightService INSTANCE = new FlightService();

    private FlightService() {
    }

    public static FlightService getINSTANCE() {
        return INSTANCE;
    }

    public List<Flight> findAll() {
        return flightDao.findAll();
    }

    public Flight create(Flight flight) {
        Long id = flightDao.create(flight);
        flight.setId(id);
        return flight;
    }

    public Flight findById(Long flightId) {
        return flightDao.findById(flightId);
    }

    public Flight findByIdWithCrew(Long flightId) {
        Flight flight = flightDao.findById(flightId);
        List<User> crew = userDao.findUsersByFlight(flight);
        flight.setCrew(crew);
        return flight;
    }
}
