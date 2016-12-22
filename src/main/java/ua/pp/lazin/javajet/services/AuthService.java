package ua.pp.lazin.javajet.services;

import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.persistence.dao.impl.postgresql.PostgresqlUserDao;
import ua.pp.lazin.javajet.persistence.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ruslan Lazin
 */
public class AuthService {

    private UserDao userDao = new PostgresqlUserDao();  //// TODO: 22.12.2016 factory

    public boolean isAuthenticated(HttpServletRequest request) {
        return true;
    }

    public void login(HttpServletRequest request, String login, String password) {
        User user = userDao.findByUsername(login);
        if (user == null) {
            return;
        }
        PasswordAuthentication.check(password, user.getPassword());
        request.getSession().setAttribute("user", user);
    }


}
