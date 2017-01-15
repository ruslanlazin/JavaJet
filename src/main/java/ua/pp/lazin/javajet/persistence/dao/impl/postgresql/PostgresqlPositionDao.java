package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.entity.Aircraft;
import ua.pp.lazin.javajet.persistence.dao.AircraftDao;
import ua.pp.lazin.javajet.persistence.dao.PositionDao;
import ua.pp.lazin.javajet.entity.Position;
import ua.pp.lazin.javajet.persistence.jdbcutils.JdbcTemplate;
import ua.pp.lazin.javajet.persistence.jdbcutils.RowMapper;
import ua.pp.lazin.javajet.persistence.jdbcutils.TransactionCallback;
import ua.pp.lazin.javajet.persistence.jdbcutils.TransactionTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class PostgresqlPositionDao implements PositionDao {
    private static final JdbcTemplate<Position> jdbcTemplate = new JdbcTemplate<>();
    private static final TransactionTemplate transactionTemplate = TransactionTemplate.getINSTANCE();

    private static final RowMapper<Position> rowMapper = new RowMapper<Position>() {
        @Override
        public Position mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Position.newBuilder()
                    .id(rs.getLong("position_id"))
                    .title(rs.getString("title"))
                    .airCrew(rs.getBoolean("air_crew"))
                    .build();
        }
    };

    private static final String CREATE =
            "INSERT INTO position (" +
                    "title, " +
                    "air_crew) VALUES(?, ?);";

    private static final String UPDATE =
            "UPDATE position SET " +
                    "title = ?, " +
                    "air_crew = ? " +
                    "WHERE position_id= ?";

    private static final String DELETE_LINKS =
            "DELETE FROM users WHERE position_id = ?";

    private static final String DELETE =
            "DELETE FROM position WHERE position_id = ?";

    private static final String FIND_BY_TITLE =
            "SELECT * FROM position p WHERE p.title = ?";

    private static final String FIND_ALL =
            "SELECT * FROM position ORDER BY title";

    private static PositionDao INSTANCE = new PostgresqlPositionDao();

    private PostgresqlPositionDao() {
    }

    public static PositionDao getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Long create(Position position) {
        return transactionTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(Connection connection) {
                return jdbcTemplate.insert(connection, CREATE,
                        position.getTitle(),
                        position.getAirCrew());
            }
        });
    }

    @Override
    public int update(Position position) {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(Connection connection) {
                return jdbcTemplate.update(connection, UPDATE,
                        position.getTitle(),
                        position.getAirCrew(),
                        position.getId());
            }
        });
    }

    @Override
    public int delete(Position position) {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(Connection connection) {
                return jdbcTemplate.update(connection, DELETE,
                        position.getId());
            }
        });
    }

    @Override
    public int deleteCascade(Position position) {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(Connection connection) {

                jdbcTemplate.update(connection, DELETE_LINKS, position.getId());

                return jdbcTemplate.update(connection, DELETE,
                        position.getId());
            }
        });
    }

    @Override
    public Position findByTitle(String title) {
        return jdbcTemplate.findEntity(rowMapper, FIND_BY_TITLE, title);
    }

    @Override
    public List<Position> findAll() {
        return jdbcTemplate.findEntities(rowMapper, FIND_ALL);
    }
}
