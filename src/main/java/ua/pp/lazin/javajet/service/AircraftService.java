package ua.pp.lazin.javajet.service;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.AircraftDao;
import ua.pp.lazin.javajet.entity.Aircraft;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import java.util.List;

/**
 * The Aircraft service.
 *
 * @author Ruslan Lazin
 */
public class AircraftService {
    private static final Logger logger = Logger.getLogger(AircraftService.class);
    private static final AircraftDao aircraftDao = DaoFactoryCreator.getFactory().getAircraftDao();
    private static final AircraftService INSTANCE = new AircraftService();

    private AircraftService() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AircraftService getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Aircraft> findAll() {
        return aircraftDao.findAll();
    }

    /**
     * Update Aircraft.
     *
     * @param aircraft the aircraft
     * @return the int
     */
    public int update(Aircraft aircraft) {
        return aircraftDao.update(aircraft);
    }

    /**
     * Create
     *
     * @param aircraft the aircraft
     * @return the long aircraft id
     */
    public Long create(Aircraft aircraft) {
        return aircraftDao.create(aircraft);
    }

    /**
     * Find by id aircraft.
     *
     * @param aircraftId the aircraft id
     * @return the aircraft
     */
    public Aircraft findById(Long aircraftId) {
        return aircraftDao.findById(aircraftId);
    }
}
