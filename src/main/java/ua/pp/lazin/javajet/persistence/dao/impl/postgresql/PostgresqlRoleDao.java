package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.entity.Aircraft;
import ua.pp.lazin.javajet.persistence.dao.RoleDao;
import ua.pp.lazin.javajet.entity.Role;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.persistence.jdbcutils.JdbcTemplate;
import ua.pp.lazin.javajet.persistence.jdbcutils.RowMapper;
import ua.pp.lazin.javajet.persistence.jdbcutils.TransactionCallback;
import ua.pp.lazin.javajet.persistence.jdbcutils.TransactionTemplate;

import java.sql.Connection;
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
    private static final TransactionTemplate transactionTemplate = TransactionTemplate.getINSTANCE();

    private static final RowMapper<Role> rowMapper = new RowMapper<Role>() {
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Role.newBuilder()
                    .id(rs.getLong("role_id"))
                    .title(rs.getString("title"))
                    .build();
        }
    };

    private static final String CREATE =
            "INSERT INTO role (title) VALUES(?);";

    @Override
    public Long create(Role role) {
        return transactionTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(Connection connection) {
                return jdbcTemplate.insert(connection, CREATE,
                        role.getTitle());
            }
        });
    }


    @Override
    public Role findByTitle(String title) {
        return jdbcTemplate.findEntity(rowMapper,
                "SELECT * FROM role r " +
                        "WHERE r.title = ?", title);
    }

    @Override
    public Role findById(Long roleId) {
        return jdbcTemplate.findEntity(rowMapper,
                "SELECT * FROM role r " +
                        "WHERE r.role_id = ?", roleId);
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
                "SELECT * FROM role r " +
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
