package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.entity.User;

import java.util.Date;
import java.util.List;

public interface FlightDao {
    Long create(Flight flight);

    List<Flight> findAllOrderByDepartureTimeAsc();

    List<Flight> findAllBforeThen(Date date);

    List<Flight> findAllLaterThen(Date date);

    int update(Flight flight);

    int delete(Flight flight);

    Flight findById(Long flightId);

    Boolean updateWithCrew(Flight flight);

    List<Flight> findAllByUserLaterThen(User user, Date date);
}
