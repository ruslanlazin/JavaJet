package ua.pp.lazin.javajet.service;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.RoleDao;
import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;
import ua.pp.lazin.javajet.util.PasswordEncoder;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class UserService {
    private final static String USER_ATTRIBUTE = "user";
    private final static Logger logger = Logger.getLogger(UserService.class);
    private final static UserDao userDao = DaoFactoryCreator.getFactory().getUserDao();
    private final static RoleDao roleDao = DaoFactoryCreator.getFactory().getRoleDao();
    private static UserService INSTANCE = new UserService();

    private UserService() {
    }

    public static UserService getINSTANCE() {
        return INSTANCE;
    }

    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute(USER_ATTRIBUTE);
    }

    public User create(User user) {   // TODO: 08.01.2017 is I need id assignment
        user.setPassword(PasswordEncoder.getSaltedHash(user.getPassword()));
        Long id = userDao.create(user);
        user.setId(id);
        return user;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public boolean isUsernameAvailable(String username) {
        return userDao.findByUsername(username) == null;
    }

    public boolean isEmailAvailable(String email) {
        return userDao.findByEmail(email) == null;
    }

    public User findById(Long id) {
        return userDao.findByID(id);
    }

    public User findByIdWithRoles(Long id) {
        User user = userDao.findByID(id);
        user.setRoles(roleDao.findRolesOfUser(user));
        return user;
    }

    public List<User> findAllWorkingAirCrewMembers() {
        return userDao.findAllWorkingAirCrewMembers();
    }

    public boolean updateWithRoles(User user) {
        return userDao.updateWithRoles(user);

    }
}
