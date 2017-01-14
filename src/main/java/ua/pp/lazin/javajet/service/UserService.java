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
    private final static String CURRENT_USER_ATTRIBUTE = "currentUser";
    private final static Logger logger = Logger.getLogger(UserService.class);
    private final static UserDao userDao = DaoFactoryCreator.getFactory().getUserDao();
    private final static RoleDao roleDao = DaoFactoryCreator.getFactory().getRoleDao();
    private static UserService INSTANCE = new UserService();

    private UserService() {
    }

    public static UserService getINSTANCE() {
        return INSTANCE;
    }

    public User findByUsernameWithRoles(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return null;
        }
        user.setRoles(roleDao.findRolesOfUser(user));
        return user;
    }


    public boolean check(String login, String password) {
        User user = userDao.findByUsername(login);
        return user != null && PasswordEncoder.check(password, user.getPassword());
    }

    public boolean isUserCachedInSession(HttpSession session) {
        return session.getAttribute(CURRENT_USER_ATTRIBUTE) != null;
    }

    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute(CURRENT_USER_ATTRIBUTE);
    }


    public void removeCurentUserFromSession(HttpSession session) {
        session.removeAttribute(CURRENT_USER_ATTRIBUTE);
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
//        check if password was changed
        if (!user.getPassword().equals(userDao.findByID(user.getId()).getPassword())) {
            user.setPassword(PasswordEncoder.getSaltedHash(user.getPassword()));
        }
        return userDao.updateWithRoles(user);
    }

    public void cacheUserInSession(String username, HttpSession session) {
        session.setAttribute(CURRENT_USER_ATTRIBUTE, findByUsernameWithRoles(username));
    }
}
