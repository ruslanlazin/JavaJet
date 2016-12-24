package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.persistence.entity.Aircraft;
import ua.pp.lazin.javajet.persistence.entity.User;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public interface AircraftDao {
    Long create(Aircraft aircraft);

    User findById(Long id);

    List<Aircraft> findAll();

    int update(Aircraft aircraft);

    int delete(Aircraft aircraft);
}
