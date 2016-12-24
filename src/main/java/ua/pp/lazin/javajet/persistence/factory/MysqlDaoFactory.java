package ua.pp.lazin.javajet.persistence.factory;

import ua.pp.lazin.javajet.persistence.dao.*;

/**
 * @author Ruslan Lazin
 */
public class MysqlDaoFactory implements DaoFactory {

    MysqlDaoFactory() {
    }

    @Override
    public AircraftDao getAircraftDao() {
        return null;
    }

    @Override
    public AirportDao getAirportDao() {
        return null;
    }

    @Override
    public FlightDao getFlightDao() {
        return null;
    }

    @Override
    public RoleDao getRoleDao() {
        return null;
    }

    @Override
    public UserDao getUserDao() {
        return null;
    }
}
