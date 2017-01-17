package ua.pp.lazin.javajet.service;

import ua.pp.lazin.javajet.entity.Airport;
import ua.pp.lazin.javajet.persistence.dao.AirportDao;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import java.util.List;

/**
 * The type Airport service.
 *
 * @author Ruslan Lazin
 */
public class AirportService {
        private static final  AirportDao airportDao = DaoFactoryCreator.getFactory().getAirportDao();
    private static final AirportService INSTANCE = new AirportService();

    private AirportService() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AirportService getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Find all
     *
     * @return the list of Airport
     */
    public List<Airport> findAll() {
        return airportDao.findAll();
    }

    /**
     * Find airport by IATA code.
     *
     * @param iataCode the iata code
     * @return the airport
     */
    public Airport findByCode(String iataCode){
        return airportDao.findByCode(iataCode);
    }
}
