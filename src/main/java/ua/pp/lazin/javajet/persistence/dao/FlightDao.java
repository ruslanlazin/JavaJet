package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.entity.User;

import java.util.Date;
import java.util.List;

/**
 * The interface Flight dao.
 */
public interface FlightDao {
    /**
     * Create long.
     *
     * @param flight the flight
     * @return the long - flight id
     */
    Long create(Flight flight);

    /**
     * Update
     * Check version and update only if they match
     *
     * @param flight the flight
     * @return the int number of updated rows
     */
    int update(Flight flight);

    /**
     * Update with crew boolean.
     * Check version and update only if they match
     *
     * @param flight the flight
     * @return the boolean is update was successful
     */
    Boolean updateWithCrew(Flight flight);

    /**
     * Delete int.
     *
     * @param flight the flight
     * @return the int number of updated rows
     */
    int delete(Flight flight);

    /**
     * Find by id flight.
     *
     * @param flightId the flight id
     * @return the flight
     */
    Flight findById(Long flightId);

    /**
     * Find all order by departure time asc list.
     *
     * @return the list of Flight
     */
    List<Flight> findAllOrderByDepartureTimeAsc();

    /**
     * Find all before then list.
     *
     * @param date the date
     * @return the list of Flight
     */
    List<Flight> findAllBeforeThen(Date date);

    /**
     * Find all later then list.
     *
     * @param date the date
     * @return the list of Flight
     */
    List<Flight> findAllLaterThen(Date date);

    /**
     * Find all by user later then list.
     *
     * @param user the user
     * @param date the date
     * @return the list of Flight
     */
    List<Flight> findAllByUserLaterThen(User user, Date date);
}
