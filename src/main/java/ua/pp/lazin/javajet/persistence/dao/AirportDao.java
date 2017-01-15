package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.entity.Airport;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public interface AirportDao {
    Long create(Airport airport);

    int update(Airport airport);

    int delete(Airport airport);

    int deleteCascade(Airport airportt);

    Airport findByCode(String iataCode);

    List<Airport> findAll();
}
