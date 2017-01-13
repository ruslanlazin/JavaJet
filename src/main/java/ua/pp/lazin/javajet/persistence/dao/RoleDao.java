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

    Role findByTitle(String title);

    Role findById(Long roleId);

    List<Role> findAll();

    Set<Role> findRolesOfUser(User user);

    int update(Role role);


    int delete(Role role);
}
