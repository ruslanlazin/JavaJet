package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.persistence.dao.AircraftDao;
import ua.pp.lazin.javajet.entity.Aircraft;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.persistence.jdbcutils.JdbcTemplate;
import ua.pp.lazin.javajet.persistence.jdbcutils.RowMapper;
import ua.pp.lazin.javajet.persistence.jdbcutils.TransactionCallback;
import ua.pp.lazin.javajet.persistence.jdbcutils.TransactionTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class PostgresqlAircraftDao implements AircraftDao {
    private static final JdbcTemplate<Aircraft> jdbcTemplate = new JdbcTemplate<>();
    private static final TransactionTemplate transactionTemplate = TransactionTemplate.getINSTANCE();

    private static final String CREATE =
            "INSERT INTO aircraft (" +
                    "reg_number, " +
                    "model) VALUES(?, ?);";

    private static final String UPDATE =
            "UPDATE aircraft SET " +
                    "reg_number = ?, " +
                    "model = ? " +
                    "WHERE aircraft_id= ?";

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
        return transactionTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(Connection connection) {
                return jdbcTemplate.insert(connection, CREATE,
                        aircraft.getRegNumber(),
                        aircraft.getModel());
            }
        });
    }


    @Override
    public int update(Aircraft aircraft) {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(Connection connection) {
                return jdbcTemplate.update(connection, UPDATE,
                        aircraft.getRegNumber(),
                        aircraft.getModel(),
                        aircraft.getId());
            }
        });
    }


    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<Aircraft> findAll() {
        return jdbcTemplate.findEntities(rowMapper, "SELECT * FROM aircraft " +
                "ORDER BY aircraft_id");
    }

    @Override
    public int delete(Aircraft aircraft) {
        return 0;
    }
}
