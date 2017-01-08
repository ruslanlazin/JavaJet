package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.persistence.entity.Position;
import ua.pp.lazin.javajet.persistence.entity.Role;
import ua.pp.lazin.javajet.persistence.entity.User;

import java.util.List;
import java.util.Set;

/**
 * @author Ruslan Lazin
 */
public interface RoleDao {
    Long create(Role role);

    Role findByTitle(String title);

    List<Role> findAll();

    Set<Role> findRolesOfUser(User user);

    int update(Role role);

    int delete(Role role);


}
