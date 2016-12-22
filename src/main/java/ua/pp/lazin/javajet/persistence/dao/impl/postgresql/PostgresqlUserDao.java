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

    private static final String READ_BY_USERNAME_SQL = "SELECT * FROM `user` WHERE username=?";

    private static final String UPDATE_SQL = "UPDATE `user` SET `username`=?, `firstName`=?," +
            " `lastName`=?, `password`=?, `discount`=? WHERE `id`=?";

    private static final String DELETE_SQL = "DELETE FROM `user` WHERE id=?";

    private static final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<User>();
    private static final RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setFirstName(rs.getString("first_name"));
            user.setSecondName(rs.getString("second_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setRole(new Role(rs.getString("title")));

            return user;
        }
    };

    private static final String CREATE_SQL = "INSERT INTO users (first_name, second_name,\n" +
            "                  username, password, email, role_id) VALUES (?, ?, ?, ?, ?);";

    @Override
    public Long create(User user) {
        jdbcTemplate.insert(CREATE_SQL, user.getFirstName(), user.getSecondName(), user.getUsername(),
                user.getPassword(), user.getEmail(), user.getRole().getRoleId());
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return jdbcTemplate.findEntity(rowMapper,
                "SELECT\n" +
                        "  u.*,\n" +
                        "  r.title\n" +
                        "FROM users u\n" +
                        "  JOIN role r ON u.role_id = r.role_id where u.username = ?", username);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.findEntities(rowMapper, "select * from users");
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
