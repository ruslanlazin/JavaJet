package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.AircraftDao;
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

    private static final String UPDATE =
            "UPDATE role SET " +
                    "title = ?, " +
                    "WHERE role_id= ?";

    private static final String DELETE_LINKS =
            "DELETE FROM users_roles WHERE role_id = ?";

    private static final String DELETE =
            "DELETE FROM role WHERE role_id = ?";

    private static final String FIND_BY_TITLE =
            "SELECT * FROM role r WHERE r.title = ?";

    private static final String FIND_BY_ID =
            "SELECT * FROM role r WHERE r.role_id = ?";

    private static final String FIND_ALL =
            "SELECT * FROM role ORDER BY title";

    private static final String FIND_BY_USER =
            "SELECT * FROM role r " +
                    "JOIN users_roles u ON r.role_id = u.role_id " +
                    "WHERE u.user_id = ?";

    private static RoleDao INSTANCE = new PostgresqlRoleDao();

    private PostgresqlRoleDao() {
    }

    public static RoleDao getINSTANCE() {
        return INSTANCE;
    }

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
    public int update(Role role) {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(Connection connection) {
                return jdbcTemplate.update(connection, UPDATE,
                        role.getTitle(),
                        role.getId());
            }
        });
    }

    @Override
    public int delete(Role role) {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(Connection connection) {
                return jdbcTemplate.update(connection, DELETE,
                        role.getId());
            }
        });
    }

    @Override
    public int deleteCascade(Role role) {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(Connection connection) {

                jdbcTemplate.update(connection, DELETE_LINKS, role.getId());

                return jdbcTemplate.update(connection, DELETE,
                        role.getId());
            }
        });
    }

    @Override
    public Role findByTitle(String title) {
        return jdbcTemplate.findEntity(rowMapper, FIND_BY_TITLE, title);
    }

    @Override
    public Role findById(Long roleId) {
        return jdbcTemplate.findEntity(rowMapper, FIND_BY_ID, roleId);
    }

    @Override
    public List<Role> findAll() {
        return jdbcTemplate.findEntities(rowMapper, FIND_ALL);
    }

    @Override
    public Set<Role> findRolesOfUser(User user) {
        List<Role> roles = jdbcTemplate.findEntities(rowMapper, FIND_BY_USER, user.getId());
        return new HashSet<Role>(roles);
    }
}
