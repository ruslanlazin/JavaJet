package ua.pp.lazin.javajet.services;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.persistence.dao.impl.postgresql.PostgresqlUserDao;
import ua.pp.lazin.javajet.persistence.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ruslan Lazin
 */
public class AuthService {
    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static Logger logger = Logger.getLogger(AuthService.class);
    private final static UserDao userDao = new PostgresqlUserDao();  //// TODO: 22.12.2016 factory

    public boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute(USER_ATTRIBUTE_NAME) != null;
    }

    public boolean login(HttpServletRequest request, String login, String password) {
        User user = userDao.findByUsername(login);
        if (user == null) {
            return false;
        }
        if (PasswordAuthentication.check(password, user.getPassword())) {
            return false;
        }
        request.getSession().setAttribute(USER_ATTRIBUTE_NAME, user);
        return true;
    }

    public void register(User user) {
        user.setPassword(PasswordAuthentication.getSaltedHash(user.getPassword()));
        userDao.create(user);
    }

}
