package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.persistence.entity.Flight;
import ua.pp.lazin.javajet.persistence.entity.User;

import java.util.List;
import java.util.Set;

/**
 * @author Ruslan Lazin
 */
public interface UserDao {
    Long create(User user);

    int update(User user);

    int delete(User user);

    User findByID(Long id);

    User findByUsername(String username);

    User findByEmail(String email);

    Set<User> findUsersByFlight(Flight flight);

    List<User> findAll();

    List<User> findAllWorkingAirCrewMembers();
}


