package ua.pp.lazin.javajet.persistence.factory;

import ua.pp.lazin.javajet.persistence.dao.AircraftDao;
import ua.pp.lazin.javajet.persistence.dao.AirportDao;
import ua.pp.lazin.javajet.persistence.dao.FlightDao;
import ua.pp.lazin.javajet.persistence.dao.PositionDao;
import ua.pp.lazin.javajet.persistence.dao.UserDao;


/**
 * @author Ruslan Lazin
 */
public interface DaoFactory {
    AircraftDao getAircraftDao();

    AirportDao getAirportDao();

    FlightDao getFlightDao();

    PositionDao getPositionDao();

    UserDao getUserDao();


}
