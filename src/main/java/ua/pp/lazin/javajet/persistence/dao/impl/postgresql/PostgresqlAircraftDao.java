package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.AircraftDao;
import ua.pp.lazin.javajet.persistence.entity.Aircraft;
import ua.pp.lazin.javajet.persistence.entity.User;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class PostgresqlAircraftDao implements AircraftDao{
    @Override
    public Long create(Aircraft aircraft) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<Aircraft> findAll() {
        return null;
    }

    @Override
    public int update(Aircraft aircraft) {
        return 0;
    }

    @Override
    public int delete(Aircraft aircraft) {
        return 0;
    }
}
