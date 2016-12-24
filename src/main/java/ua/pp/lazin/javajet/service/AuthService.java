package ua.pp.lazin.javajet.service;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.persistence.dao.impl.postgresql.PostgresqlUserDao;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;
import ua.pp.lazin.javajet.util.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ruslan Lazin
 */
public class AuthService {
    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static Logger logger = Logger.getLogger(AuthService.class);
    private final static UserDao userDao = DaoFactoryCreator.getFactory().getUserDao();
    private static AuthService INSTANCE = new AuthService();

    private AuthService() {
    }

    public static AuthService getINSTANCE() {
        return INSTANCE;
    }

    public boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute(USER_ATTRIBUTE_NAME) != null;
    }

    public boolean login(HttpServletRequest request, String login, String password) {
        User user = userDao.findByUsername(login);
        if (user == null) {
            return false;
        }
        if (PasswordEncoder.check(password, user.getPassword())) {
            request.getSession().setAttribute(USER_ATTRIBUTE_NAME, user);
            return true;
        }
        return false;
    }

    public void logout(HttpServletRequest request) {
        request.getSession().setAttribute(USER_ATTRIBUTE_NAME, null);
    }

    public void register(User user) {
        user.setPassword(PasswordEncoder.getSaltedHash(user.getPassword()));
        userDao.create(user);
    }

}
