package ua.pp.lazin.javajet.filters;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.persistence.dao.impl.postgresql.PostgresqlUserDao;
import ua.pp.lazin.javajet.persistence.entity.Role;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.persistence.jdbcutils.ConnectionManager;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;
import ua.pp.lazin.javajet.services.AuthService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Ruslan Lazin
 */

@WebFilter("/*")
public class AuthFilter implements Filter {
    private final static Logger logger = Logger.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String url = request.getServletPath();
//        boolean allowedRequest = false;
//
//            allowedRequest = true;
//
//
//        if (!allowedRequest) {
//            HttpSession session = request.getSession(false);
//            if (null == session) {
//                response.sendRedirect("index.jsp");
//            }
//        }

        logger.debug("request " + request.getRequestURI());
        Object username = request.getSession().getAttribute("username");


        UserDao userDao = new PostgresqlUserDao();
        System.out.println(userDao.findByUsername("b"));
//        User user = new User();
//        user.setUserId(null);
//        user.setFirstName("bill");
//        user.setSecondName("bobikov");
//        user.setUsername("b");
//        user.setPassword("b");
//        user.setEmail("b@b.ua");
//        Role role = new Role();
//        role.setRoleId(1L);
//        user.setRole(role);
//        AuthService authService = new AuthService();
//        authService.register(user);

        logger.debug("username=" + username);
        if (username == null && (!request.getRequestURI().equals("/login"))) {
            response.sendRedirect("login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
