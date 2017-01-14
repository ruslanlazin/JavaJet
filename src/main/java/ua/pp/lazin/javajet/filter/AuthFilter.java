package ua.pp.lazin.javajet.filter;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.service.UserService;

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
    private final static UserService userService = UserService.getINSTANCE();
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

        if (path.startsWith(RESOURCES_URI_PREFIX) || userService.isUserCachedInSession(request.getSession())
                || path.equals(LOGIN_URI)) {
            if (logger.isDebugEnabled()) {
                User user = userService.getCurrentUser(request.getSession());
                logger.debug("Request from user: " + user + " to: " + path + " - accepted");
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
