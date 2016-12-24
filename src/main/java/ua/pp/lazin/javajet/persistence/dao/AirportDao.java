package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.persistence.entity.Airport;
import ua.pp.lazin.javajet.persistence.entity.User;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public interface AirportDao {
    Long create(Airport airport);

    User findByCode(String code);

    List<Airport> findAll();

    int update(Airport airport);

    int delete(Airport airport);
}
