package ua.pp.lazin.javajet.service;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.AircraftDao;
import ua.pp.lazin.javajet.entity.Aircraft;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class AircraftService {
    private static final Logger logger = Logger.getLogger(AircraftService.class);
    private static final AircraftDao aircraftDao = DaoFactoryCreator.getFactory().getAircraftDao();
    private static final AircraftService INSTANCE = new AircraftService();

    private AircraftService() {
    }

    public static AircraftService getINSTANCE() {
        return INSTANCE;
    }

    public List<Aircraft> findAll() {
        return aircraftDao.findAll();
    }
}
