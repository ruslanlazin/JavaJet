package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.RoleDao;
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
public class PostgresqlRoleDao implements RoleDao{
    private static final JdbcTemplate<Role> jdbcTemplate = new JdbcTemplate<>();
    private static final RowMapper<Role> rowMapper = new RowMapper<Role>() {
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role();
            role.setId(rs.getLong("role_id"));
            role.setTitle(rs.getString("title"));
            return role;
        }
    };

    @Override
    public Long create(Role role) {
        return null;
    }

    @Override
    public User findById(Role role) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return jdbcTemplate.findEntities(rowMapper, "SELECT * FROM role ORDER BY title");
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
