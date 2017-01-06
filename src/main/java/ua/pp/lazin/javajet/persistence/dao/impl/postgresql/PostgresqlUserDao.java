package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.persistence.entity.Flight;
import ua.pp.lazin.javajet.persistence.entity.Position;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.persistence.jdbcutils.JdbcTemplate;
import ua.pp.lazin.javajet.persistence.jdbcutils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ruslan Lazin
 */
public class PostgresqlUserDao implements UserDao {


    private static final String FIND_ALL_SQL = "SELECT * FROM `user`";

    private static final String READ_SQL = "SELECT * FROM `user` WHERE id=?";

    private static final String READ_BY_USERNAME_SQL = "SELECT * FROM `user` WHERE username = ?";

    private static final String UPDATE_SQL = "";

    private static final String DELETE_SQL = "DELETE FROM `user` WHERE id=?";

    private static final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();
    private static final RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            Position position = new Position();
            position.setId(rs.getLong("position_id"));
            position.setTitle(rs.getString("title"));

            return User.newBuilder()
                    .id(rs.getLong("user_id"))
                    .position(position)
                    .firstName(rs.getString("first_name"))
                    .secondName(rs.getString("second_name"))
                    .username(rs.getString("username"))
                    .password(rs.getString("password"))
                    .email(rs.getString("email"))
                    .airCrew(rs.getBoolean("air_crew"))
                    .works(rs.getBoolean("works"))
                    .version(rs.getInt("version"))
                    .build();
        }
    };

    
    private static final String CREATE_SQL = "INSERT INTO users (first_name, second_name,\n" +
            " username, password, email, position_id) VALUES (?, ?, ?, ?, ?, ?);";

    @Override
    public Long create(User user) {
        return jdbcTemplate.insert(CREATE_SQL, user.getFirstName(), user.getSecondName(), user.getUsername(),
                user.getPassword(), user.getEmail(), user.getPosition().getId());
    }

    @Override
    public User findByID(Long id) {
        return jdbcTemplate.findEntity(rowMapper,
                "SELECT u.*, r.title FROM users u JOIN position r ON u.position_id = r.position_id where u.user_id = ?", id);

    }

    @Override
    public User findByUsername(String username) {
        return jdbcTemplate.findEntity(rowMapper,
                "SELECT u.*, r.title FROM users u JOIN position r ON u.position_id = r.position_id where u.username = ?", username);
    }

    @Override
    public Set<User> findUsersByFlight(Flight flight) {

        List<User> crew =  jdbcTemplate.findEntities(rowMapper, "SELECT u.*, r.title FROM users u JOIN position r ON u.position_id = r.position_id JOIN flight_users f ON u.user_id = f.user_id " +
                "WHERE f.flight_id = ?", flight.getId());
        return new HashSet<>(crew);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.findEntities(rowMapper, "SELECT u.*, r.title FROM users u JOIN position r ON u.position_id = r.position_id ORDER BY u.second_name");
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int delete(User user) {
        return 0;
    }

    @Override
    public User findByEmail(String email) {
        return jdbcTemplate.findEntity(rowMapper,
                "SELECT u.*, r.title FROM users u JOIN position r ON u.position_id = r.position_id where u.email = ?", email);
    }
}
