package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.entity.Airport;

import java.util.List;

/**
 * The interface Airport dao.
 *
 * @author Ruslan Lazin
 */
public interface AirportDao {
    /**
     * Create Airport
     *
     * @param airport the airport
     * @return the long - airport id
     */
    Long create(Airport airport);

    /**
     * Update int.
     *
     * @param airport the airport
     * @return the int number of updated rows
     */
    int update(Airport airport);

    /**
     * Delete int.
     *
     * @param airport the airport
     * @return the int number of deleted rows
     */
    int delete(Airport airport);

    /**
     * Delete cascade int.
     * <p>
     * Warn! All Flights with that Airport will be deleted as well!
     *
     * @param airport the airport
     * @return the int number of deleted rows
     */
    int deleteCascade(Airport airport);

    /**
     * Find by code airport.
     *
     * @param iataCode the iata code
     * @return the airport
     */
    Airport findByCode(String iataCode);

    /**
     * Find all list.
     *
     * @return the list of Airport
     */
    List<Airport> findAll();
}
