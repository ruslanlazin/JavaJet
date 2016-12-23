package ua.pp.lazin.javajet.filters;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.service.AuthService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Ruslan Lazin
 */

@WebFilter("/")
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



//        UserDao userDao = new PostgresqlUserDao();
//
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

        AuthService authService = new AuthService();
        if (!authService.isAuthenticated(request)) {
            logger.debug("checkandadd");
            System.out.println(authService.login(request, "b", "b"));
        }
        User user = (User) request.getSession().getAttribute("user");

        logger.debug("user=" + user);
        if (user == null && (!request.getRequestURI().equals("/login"))) {
            response.sendRedirect("login");
            return;
        }

        System.out.println(request.getMethod());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
