package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.entity.Aircraft;

import java.util.List;

/**
 * The interface Aircraft dao.
 *
 * @author Ruslan Lazin
 */
public interface AircraftDao {
    /**
     * Create aircraft.
     *
     * @param aircraft the aircraft
     * @return the long - aircraft id
     */
    Long create(Aircraft aircraft);

    /**
     * Update aircraft
     *
     * @param aircraft the aircraft
     * @return the int number of updated rows
     */
    int update(Aircraft aircraft);

    /**
     * Delete int.
     *
     * @param aircraft the aircraft
     * @return the int number of deleted rows
     */
    int delete(Aircraft aircraft);

    /**
     * Delete cascade
     *
     * @param aircraft the aircraft
     * @return the int number of deleted rows
     */
    int deleteCascade(Aircraft aircraft);

    /**
     * Find by id aircraft.
     *
     * Warn! All Flights with that Aircraft will be deleted as well!
     *
     * @param id the id
     * @return the aircraft
     */
    Aircraft findById(Long id);

    /**
     * Find all list.
     *
     * @return the list of Aircraft
     */
    List<Aircraft> findAll();
}
