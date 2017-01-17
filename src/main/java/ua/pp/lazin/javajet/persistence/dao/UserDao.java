package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.entity.User;

import java.util.List;
import java.util.Set;

/**
 * The interface User dao.
 *
 * @author Ruslan Lazin
 */
public interface UserDao {
    /**
     * Create long.
     *
     * @param user the user
     * @return the long - user id
     */
    Long create(User user);

    /**
     * Update int.
     * Check version and update only if they match
     *
     * @param user the user
     * @return the int number of updated rows
     */
    int update(User user);

    /**
     * Update with roles boolean.
     * Check version and update only if they match
     *
     * @param user the user
     * @return the boolean is update was successful
     */
    Boolean updateWithRoles(User user);

    /**
     * Delete int.
     *
     * @param user the user
     * @return the int number of deleted rows
     */
    int delete(User user);

    /**
     * Delete cascade int.
     *
     * @param user the user
     * @return the int number of deleted rows
     */
    int deleteCascade(User user);

    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    User findByID(Long id);

    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);

    /**
     * Find by email user.
     *
     * @param email the email
     * @return the user
     */
    User findByEmail(String email);

    /**
     * Find users by flight set.
     *
     * @param flight the flight
     * @return the set of User
     */
    Set<User> findUsersByFlight(Flight flight);

    /**
     * Find all list.
     *
     * @return the list of User
     */
    List<User> findAll();

    /**
     * Find all working air crew members list.
     *
     * @return the list of User
     */
    List<User> findAllWorkingAirCrewMembers();
}


