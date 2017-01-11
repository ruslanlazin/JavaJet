package ua.pp.lazin.javajet.service;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.AirportDao;
import ua.pp.lazin.javajet.entity.Airport;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class AirportService {
    private static final  Logger logger = Logger.getLogger(AirportService.class);
    private static final  AirportDao airportDao = DaoFactoryCreator.getFactory().getAirportDao();
    private static final AirportService INSTANCE = new AirportService();

    private AirportService() {
    }

    public static AirportService getINSTANCE() {
        return INSTANCE;
    }

    public List<Airport> findAll() {
        return airportDao.findAll();
    }

    public Airport findByCode(String iataCode){
        return airportDao.findByCode(iataCode);
    }
}
