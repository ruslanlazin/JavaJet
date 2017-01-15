package ua.pp.lazin.javajet.persistence.factory;

import ua.pp.lazin.javajet.persistence.dao.*;
import ua.pp.lazin.javajet.persistence.dao.impl.mysql.*;

/**
 * @author Ruslan Lazin
 */
public class MysqlDaoFactory implements DaoFactory {

    MysqlDaoFactory() {
    }

    @Override
    public AircraftDao getAircraftDao() {
        return MysqlAircraftDao.getINSTANCE();
    }

    @Override
    public AirportDao getAirportDao() {
        return MysqlAirportDao.getINSTANCE();
    }

    @Override
    public FlightDao getFlightDao() {
        return MysqlFlightDao.getINSTANCE();
    }

    @Override
    public PositionDao getPositionDao() {
        return MysqlPositionDao.getINSTANCE();
    }

    @Override
    public UserDao getUserDao() {
        return MysqlUserDao.getINSTANCE();
    }

    @Override
    public RoleDao getRoleDao() {
        return MysqlRoleDao.getINSTANCE();
    }
}
