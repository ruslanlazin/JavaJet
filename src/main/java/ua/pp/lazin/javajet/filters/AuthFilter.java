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

@WebFilter("/*")
public class AuthFilter implements Filter {
    private final static Logger logger = Logger.getLogger(AuthFilter.class);
    private final static AuthService authService = new AuthService();
    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static String LOGIN_URI = "/login";
    private final static String RESOURCES_URI = "/resources/";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getServletPath();
        System.err.println(url + ".sevletPath");
        System.err.println(request.getContextPath() + ".contextpath");
        System.err.println(request.getRequestURI() + ".requestUri");
        System.err.println(request.getPathInfo() + ".pathiifo");

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
        String path = request.getRequestURI().substring(request.getContextPath().length());

        if (authService.isAuthenticated(request) || path.equals(LOGIN_URI) ||
                path.startsWith(RESOURCES_URI)) {
            if (logger.isDebugEnabled()) {
                User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE_NAME);
                logger.debug("Request from user: " + user + " to: " + path);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            logger.debug("Redirect to: " + LOGIN_URI);
            response.sendRedirect(request.getContextPath() + LOGIN_URI);
        }
    }


    @Override
    public void destroy() {
    }
}
