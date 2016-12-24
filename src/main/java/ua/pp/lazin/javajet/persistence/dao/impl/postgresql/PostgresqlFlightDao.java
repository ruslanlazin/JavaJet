package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.FlightDao;
import ua.pp.lazin.javajet.persistence.entity.Flight;
import ua.pp.lazin.javajet.persistence.entity.User;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class PostgresqlFlightDao implements FlightDao{
    @Override
    public Long create(Flight flight) {
        return null;
    }

    @Override
    public User findByID(Long id) {
        return null;
    }

    @Override
    public List<Flight> findAll() {
        return null;
    }

    @Override
    public List<Flight> findAllExpected() {
        return null;
    }

    @Override
    public int update(Flight flight) {
        return 0;
    }

    @Override
    public int delete(Flight flight) {
        return 0;
    }
}
