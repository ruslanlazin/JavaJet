package ua.pp.lazin.javajet.persistence.factory;

import ua.pp.lazin.javajet.persistence.dao.*;


/**
 * The interface Dao factory.
 *
 * @author Ruslan Lazin
 */
public interface DaoFactory {
    /**
     * Gets aircraft dao.
     *
     * @return the aircraft dao
     */
    AircraftDao getAircraftDao();

    /**
     * Gets airport dao.
     *
     * @return the airport dao
     */
    AirportDao getAirportDao();

    /**
     * Gets flight dao.
     *
     * @return the flight dao
     */
    FlightDao getFlightDao();

    /**
     * Gets position dao.
     *
     * @return the position dao
     */
    PositionDao getPositionDao();

    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    UserDao getUserDao();

    /**
     * Gets role dao.
     *
     * @return the role dao
     */
    RoleDao getRoleDao();
}
