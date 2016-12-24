package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.RoleDao;
import ua.pp.lazin.javajet.persistence.entity.Role;
import ua.pp.lazin.javajet.persistence.entity.User;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class PostgresqlRoleDao implements RoleDao{
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
        return null;
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
