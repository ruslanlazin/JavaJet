package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.persistence.entity.Flight;
import ua.pp.lazin.javajet.persistence.entity.User;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public interface FlightDao {
    Long create(Flight flight);

    List<Flight> findAll();

    List<Flight> findAllExpected();

    int update(Flight flight);

    int delete(Flight flight);

    Flight findById(Long flightId);

    void updateCrew(Flight flight);
}
