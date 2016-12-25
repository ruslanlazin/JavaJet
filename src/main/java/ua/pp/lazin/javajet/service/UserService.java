package ua.pp.lazin.javajet.service;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;
import ua.pp.lazin.javajet.util.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ruslan Lazin
 */
public class UserService {
    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static Logger logger = Logger.getLogger(UserService.class);
    private final static UserDao userDao = DaoFactoryCreator.getFactory().getUserDao();
    private static UserService INSTANCE = new UserService();

    private UserService() {
    }

    public static UserService getINSTANCE() {
        return INSTANCE;
    }

    public User getCurrentUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(USER_ATTRIBUTE_NAME);
    }

    public void register(User user) {
        user.setPassword(PasswordEncoder.getSaltedHash(user.getPassword()));
        userDao.create(user);
    }

}
