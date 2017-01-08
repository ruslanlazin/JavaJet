package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.PositionDao;
import ua.pp.lazin.javajet.persistence.dao.RoleDao;
import ua.pp.lazin.javajet.persistence.entity.Position;
import ua.pp.lazin.javajet.persistence.entity.Role;
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
public class PostgresqlRoleDao implements RoleDao {
    private static final JdbcTemplate<Role> jdbcTemplate = new JdbcTemplate<>();
    private static final RowMapper<Role> rowMapper = new RowMapper<Role>() {
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Role.newBuilder()
                    .id(rs.getLong("role_id"))
                    .title(rs.getString("title"))
                    .build();
        }
    };


    @Override
    public Long create(Role role) {
        return null;
    }

    @Override
    public Role findByTitle(String title) {
        return jdbcTemplate.findEntity(rowMapper,
                "SELECT * FROM role r " +
                        "WHERE r.title = ?", title);
    }

    @Override
    public List<Role> findAll() {
        return jdbcTemplate.findEntities(rowMapper,
                "SELECT * FROM role " +
                        "ORDER BY title");
    }

    @Override
    public Set<Role> findRolesOfUser(User user) {
        List<Role> roles = jdbcTemplate.findEntities(rowMapper,
                "SELECT * FROM roles r" +
                        "JOIN users_roles u ON r.role_id = u.role_id " +
                        "WHERE u.user_id = ?", user.getId());
        return new HashSet<Role>(roles);
    }

    @Override
    public int update(Role role) {
        return 0;
    }

    @Override
    public int delete(Role role) {
        return 0;
    }

}
