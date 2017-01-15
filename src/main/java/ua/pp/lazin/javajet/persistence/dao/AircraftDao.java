package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.entity.Aircraft;
import ua.pp.lazin.javajet.entity.User;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public interface AircraftDao {
    Long create(Aircraft aircraft);

    int update(Aircraft aircraft);

    int delete(Aircraft aircraft);

    int deleteCascade(Aircraft aircraft);

    Aircraft findById(Long id);

    List<Aircraft> findAll();
}
