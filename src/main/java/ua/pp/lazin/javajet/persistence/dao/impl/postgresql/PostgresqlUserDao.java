package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.persistence.entity.Role;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.persistence.jdbcutils.JdbcTemplate;
import ua.pp.lazin.javajet.persistence.jdbcutils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class PostgresqlUserDao implements UserDao {


    private static final String FIND_ALL_SQL = "SELECT * FROM `user`";

    private static final String READ_SQL = "SELECT * FROM `user` WHERE id=?";

    private static final String READ_BY_USERNAME_SQL = "SELECT * FROM `user` WHERE username = ?";

    private static final String UPDATE_SQL = "UPDATE `user` SET `username`=?, `firstName`=?," +
            " `lastName`=?, `password`=?, `discount`=? WHERE `id`=?";

    private static final String DELETE_SQL = "DELETE FROM `user` WHERE id=?";

    private static final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();
    private static final RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("user_id"));
            user.setFirstName(rs.getString("first_name"));
            user.setSecondName(rs.getString("second_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            Role role = new Role();
            role.setId(rs.getLong("role_id"));
            role.setTitle(rs.getString("title"));
            user.setRole(role);

            return user;
        }
    };

    private static final String CREATE_SQL = "INSERT INTO users (first_name, second_name,\n" +
            "                  username, password, email, role_id) VALUES (?, ?, ?, ?, ?, ?);";

    @Override
    public Long create(User user) {
        return jdbcTemplate.insert(CREATE_SQL, user.getFirstName(), user.getSecondName(), user.getUsername(),
                user.getPassword(), user.getEmail(), user.getRole().getId());
    }

    @Override
    public User findByUsername(String username) {
        return jdbcTemplate.findEntity(rowMapper,
                "SELECT u.*, r.title FROM users u JOIN role r ON u.role_id = r.role_id where u.username = ?", username);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.findEntities(rowMapper, "SELECT u.*, r.title FROM users u JOIN role r ON u.role_id = r.role_id");
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
                "SELECT u.*, r.title FROM users u JOIN role r ON u.role_id = r.role_id where u.email = ?", email);
    }
}
