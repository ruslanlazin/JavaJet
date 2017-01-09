package ua.pp.lazin.javajet.persistence.factory;

import ua.pp.lazin.javajet.persistence.dao.*;
import ua.pp.lazin.javajet.persistence.entity.Role;


/**
 * @author Ruslan Lazin
 */
public interface DaoFactory {
    AircraftDao getAircraftDao();

    AirportDao getAirportDao();

    FlightDao getFlightDao();

    PositionDao getPositionDao();

    UserDao getUserDao();

    RoleDao getRoleDao();
}
