package ua.pp.lazin.javajet.service;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.RoleDao;
import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;
import ua.pp.lazin.javajet.util.PasswordEncoder;

import javax.servlet.http.HttpSession;

/**
 * @author Ruslan Lazin
 */
public class AuthService {
    private final static String USER_ATTRIBUTE = "user";
    private final static Logger logger = Logger.getLogger(AuthService.class);
    private final static UserDao userDao = DaoFactoryCreator.getFactory().getUserDao();
    private final static RoleDao roleDao = DaoFactoryCreator.getFactory().getRoleDao();
    private static AuthService INSTANCE = new AuthService();

    private AuthService() {
    }

    public static AuthService getINSTANCE() {
        return INSTANCE;
    }

    public boolean isAuthenticated(HttpSession session) {
        return session.getAttribute(USER_ATTRIBUTE) != null;
    }

    public boolean login(HttpSession session, String login, String password) {
        User user = userDao.findByUsername(login);
        if (user == null) {
            return false;
        }
        if (PasswordEncoder.check(password, user.getPassword())) {
            user.setRoles(roleDao.findRolesOfUser(user));
            session.setAttribute(USER_ATTRIBUTE, user);
            return true;
        }
        return false;
    }

    public void logout(HttpSession session) {


        session.removeAttribute(USER_ATTRIBUTE);
    }

    public void register(User user) { // TODO: 09.01.2017 remove unused
        user.setPassword(PasswordEncoder.getSaltedHash(user.getPassword()));
        userDao.create(user);
    }

    public User login(String login, String password) {
        User user = userDao.findByUsername(login);
        if (user == null) {
            return null;
        }
        if (PasswordEncoder.check(password, user.getPassword())) {
            user.setRoles(roleDao.findRolesOfUser(user));
            return user;
        }
        return null;
    }
}
