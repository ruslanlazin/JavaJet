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
        return new PostgresqlAircraftDao();
    }

    @Override
    public AirportDao getAirportDao() {
        return new PostgresqlAirportDao();
    }

    @Override
    public FlightDao getFlightDao() {
        return new PostgresqlFlightDao();
    }

    @Override
    public PositionDao getPositionDao() {
        return new PostgresqlPositionDao();
    }

    @Override
    public UserDao getUserDao() {
        return new PostgresqlUserDao();
    }
}
