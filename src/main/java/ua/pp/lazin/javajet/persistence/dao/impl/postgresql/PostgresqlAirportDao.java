package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.AirportDao;
import ua.pp.lazin.javajet.persistence.entity.Airport;
import ua.pp.lazin.javajet.persistence.entity.User;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class PostgresqlAirportDao implements AirportDao{
    @Override
    public Long create(Airport airport) {
        return null;
    }

    @Override
    public User findByCode(String code) {
        return null;
    }

    @Override
    public List<Airport> findAll() {
        return null;
    }

    @Override
    public int update(Airport airport) {
        return 0;
    }

    @Override
    public int delete(Airport airport) {
        return 0;
    }
}
