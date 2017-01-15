package ua.pp.lazin.javajet.persistence.factory;

import ua.pp.lazin.javajet.persistence.dao.*;
import ua.pp.lazin.javajet.persistence.dao.impl.postgresql.*;

/**
 * @author Ruslan Lazin
 */
class PostgresqlDaoFactory implements DaoFactory {

    PostgresqlDaoFactory() {
    }

    @Override
    public AircraftDao getAircraftDao() {
        return PostgresqlAircraftDao.getINSTANCE();
    }

    @Override
    public AirportDao getAirportDao() {
        return PostgresqlAirportDao.getINSTANCE();
    }

    @Override
    public FlightDao getFlightDao() {
        return PostgresqlFlightDao.getINSTANCE();
    }

    @Override
    public PositionDao getPositionDao() {
        return PostgresqlPositionDao.getINSTANCE();
    }

    @Override
    public UserDao getUserDao() {
        return PostgresqlUserDao.getINSTANCE();
    }

    @Override
    public RoleDao getRoleDao() {
        return PostgresqlRoleDao.getINSTANCE();
    }
}
