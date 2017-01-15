package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.entity.Role;
import ua.pp.lazin.javajet.entity.User;

import java.util.List;
import java.util.Set;

/**
 * @author Ruslan Lazin
 */
public interface RoleDao {
    Long create(Role role);

    int update(Role role);

    int delete(Role role);

    int deleteCascade(Role role);

    Role findById(Long roleId);

    Role findByTitle(String title);

    Set<Role> findRolesOfUser(User user);

    List<Role> findAll();
}
