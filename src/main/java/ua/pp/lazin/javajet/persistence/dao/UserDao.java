package ua.pp.lazin.javajet.persistence.dao;

import ua.pp.lazin.javajet.persistence.entity.User;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public interface UserDao {
    Long create(User user);

    User findByUsername(String username);

    List<User> findAll();

    int update(User user);

    int delete(User user);
}
