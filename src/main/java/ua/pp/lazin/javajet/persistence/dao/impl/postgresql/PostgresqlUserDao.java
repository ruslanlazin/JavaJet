package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.entity.Role;
import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.entity.Position;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.persistence.jdbcutils.JdbcTemplate;
import ua.pp.lazin.javajet.persistence.jdbcutils.RowMapper;
import ua.pp.lazin.javajet.persistence.jdbcutils.TransactionCallback;
import ua.pp.lazin.javajet.persistence.jdbcutils.TransactionTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ruslan Lazin
 */
public class PostgresqlUserDao implements UserDao {
    private static final TransactionTemplate transactionTemplate = TransactionTemplate.getINSTANCE();
    private static final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();
    private static final Integer START_VERSION = 0;

    private static final RowMapper<User> rowMapper = new RowMapper<User>() {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            Position position = Position.newBuilder()
                    .id(rs.getLong("position_id"))
                    .title(rs.getString("title"))
                    .airCrew(rs.getBoolean("air_crew"))
                    .build();
            return User.newBuilder()
                    .id(rs.getLong("user_id"))
                    .position(position)
                    .firstName(rs.getString("first_name"))
                    .secondName(rs.getString("second_name"))
                    .username(rs.getString("username"))
                    .password(rs.getString("password"))
                    .email(rs.getString("email"))
                    .working(rs.getBoolean("working"))
                    .version(rs.getInt("version"))
                    .build();
        }
    };


    private static final String CREATE_SQL = "INSERT INTO users (" +
            "first_name, " +
            "second_name, " +
            "username, " +
            "password, " +
            "email, " +
            "position_id, " +
            "working, " +
            "version ) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";


    private static final String INSERT_LINK =
            "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";

    @Override
    public Long create(User user) {
        return transactionTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(Connection connection) {
                Long generatedUserId = jdbcTemplate.insert(connection, CREATE_SQL,
                        user.getFirstName(),
                        user.getSecondName(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getEmail(),
                        user.getPosition().getId(),
                        user.isWorking(),
                        START_VERSION);

                List<Object[]> rows = new ArrayList<>();
                if (user.getRoles() != null) {
                    for (Role role : user.getRoles()) {
                        rows.add(new Object[]{generatedUserId, role.getId()});
                    }
                    jdbcTemplate.batchUpdate(connection, INSERT_LINK, rows);
                }
                return generatedUserId;
            }
        });
    }


    @Override
    public User findByID(Long id) {
        return jdbcTemplate.findEntity(rowMapper,
                "SELECT u.*, p.title, p.air_crew FROM users u " +
                        "JOIN position p ON u.position_id = p.position_id " +
                        "WHERE u.user_id = ?", id);

    }

    @Override
    public User findByUsername(String username) {
        return jdbcTemplate.findEntity(rowMapper,
                "SELECT u.*, p.title, p.air_crew FROM users u " +
                        "JOIN position p ON u.position_id = p.position_id " +
                        "WHERE u.username = ?", username);
    }

    @Override
    public User findByEmail(String email) {
        return jdbcTemplate.findEntity(rowMapper,
                "SELECT u.*, p.title, p.air_crew FROM users u " +
                        "JOIN position p ON u.position_id = p.position_id " +
                        "WHERE u.email = ?", email);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.findEntities(rowMapper,
                "SELECT u.*, p.title, p.air_crew FROM users u " +
                        "JOIN position p ON u.position_id = p.position_id " +
                        "ORDER BY u.second_name");
    }

    @Override
    public Set<User> findUsersByFlight(Flight flight) {
        List<User> crew = jdbcTemplate.findEntities(rowMapper,
                "SELECT u.*, p.title, p.air_crew FROM users u " +
                        "JOIN position p ON u.position_id = p.position_id " +
                        "JOIN flight_users f ON u.user_id = f.user_id " +
                        "WHERE f.flight_id = ?", flight.getId());
        return new HashSet<User>(crew);
    }

    @Override
    public List<User> findAllWorkingAirCrewMembers() {
        return jdbcTemplate.findEntities(rowMapper,
                "SELECT u.*, p.title, p.air_crew FROM users u " +
                        "JOIN position p ON u.position_id = p.position_id " +
                        "WHERE u.working = TRUE " +
                        "AND p.air_crew = TRUE " +
                        "ORDER BY u.second_name");
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int delete(User user) {
        return 0;
    }
}
