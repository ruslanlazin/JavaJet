package ua.pp.lazin.javajet.filters;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.service.AuthService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Ruslan Lazin
 */

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    private final static Logger logger = Logger.getLogger(AuthFilter.class);
    private final static AuthService authService = AuthService.getINSTANCE();
    private final static String USER_ATTRIBUTE = "user";
    private final static String LOGIN_URI = "/login";
    private final static String RESOURCES_URI_PREFIX = "/resources/";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().substring(request.getContextPath().length());

        if (path.startsWith(RESOURCES_URI_PREFIX) || authService.isAuthenticated(request.getSession())
                || path.equals(LOGIN_URI)) {
            if (logger.isDebugEnabled()) {
                User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE);
                logger.debug("Request from user: " + user + " to: " + path + " - accepted");

                if (user != null) {
                    System.out.println(user.getRoles());
                }


            }
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Redirect to: " + LOGIN_URI);
            }
            response.sendRedirect(request.getContextPath() + LOGIN_URI);
        }
    }

    @Override
    public void destroy() {
    }
}
