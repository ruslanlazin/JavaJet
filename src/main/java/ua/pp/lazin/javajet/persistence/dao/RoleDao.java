package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.persistence.entity.Role;
import ua.pp.lazin.javajet.persistence.entity.User;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public interface RoleDao {
    Long create(Role role);

    Role findByTitle(String title);

    List<Role> findAll();

    int update(Role role);

    int delete(Role role);
}
