package ua.pp.lazin.javajet.service;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.AirportDao;
import ua.pp.lazin.javajet.persistence.dao.FlightDao;
import ua.pp.lazin.javajet.persistence.entity.Airport;
import ua.pp.lazin.javajet.persistence.entity.Flight;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class FlightService {
    private final static Logger logger = Logger.getLogger(FlightService.class);
    private final static FlightDao flightDao = DaoFactoryCreator.getFactory().getFlightDao();
    private static FlightService INSTANCE = new FlightService();

    private FlightService() {
    }

    public static FlightService getINSTANCE() {
        return INSTANCE;
    }

    public List<Flight> findAll() {
        return flightDao.findAll();
    }

//    public Airport findByCode(String iataCode){
//        return airportDao.findByCode(iataCode);
//    }
}
