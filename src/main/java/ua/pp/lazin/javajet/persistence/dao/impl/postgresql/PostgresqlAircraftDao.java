package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.AircraftDao;
import ua.pp.lazin.javajet.persistence.entity.Aircraft;
import ua.pp.lazin.javajet.persistence.entity.Airport;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.persistence.jdbcutils.JdbcTemplate;
import ua.pp.lazin.javajet.persistence.jdbcutils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class PostgresqlAircraftDao implements AircraftDao {
    private static final JdbcTemplate<Aircraft> jdbcTemplate = new JdbcTemplate<>();
    private static final RowMapper<Aircraft> rowMapper = new RowMapper<Aircraft>() {

        @Override
        public Aircraft mapRow(ResultSet rs, int rowNum) throws SQLException {

            return Aircraft.newBuilder()
                    .id(rs.getLong("aircraft_id"))
                    .model(rs.getString("model"))
                    .regNumber(rs.getString("reg_number")).build();
        }
    };

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
        return jdbcTemplate.findEntities(rowMapper, "SELECT * FROM aircraft");
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
