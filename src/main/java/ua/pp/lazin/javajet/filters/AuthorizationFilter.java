package ua.pp.lazin.javajet.filters;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.service.AuthService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author Ruslan Lazin
 */

@WebFilter(filterName = "AuthorizationFilter")
public class AuthorizationFilter implements Filter {
    private final static Logger logger = Logger.getLogger(AuthorizationFilter.class);
    private final static AuthService authService = AuthService.getINSTANCE();
    private final static String USER_ATTRIBUTE = "user";
    private final static String LOGIN_URI = "/login";
    private final static String RESOURCES_URI_PREFIX = "/resources/";

    private String errorPage;


    @Override
    /**Filter should be configured with an system error page.*/
    public void init(FilterConfig FilterConfig) throws ServletException {
        if (FilterConfig != null) {
            errorPage = FilterConfig.getInitParameter("error_page");
        }
    }

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String path = request.getRequestURI().substring(request.getContextPath().length());
//
//        if (path.startsWith(RESOURCES_URI_PREFIX) || authService.isAuthenticated(request.getSession())
//                || path.equals(LOGIN_URI)) {
//            if (logger.isDebugEnabled()) {
//                User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE);
//                logger.debug("Request from user: " + user + " to: " + path + " - accepted");
//
//                if (user != null) {
//                    System.out.println(user.getRoles());
//                }
//
//
//            }
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            if (logger.isDebugEnabled()) {
//                logger.debug("Redirect to: " + LOGIN_URI);
//            }
//            response.sendRedirect(request.getContextPath() + LOGIN_URI);
//        }
//    }


    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws ServletException, IOException {
        if (errorPage == null) {
            returnError(request, response, "AuthorizationFilter not" +
                    "properly configured! Contact Administrator.");

        }

        HttpServletRequest t = (HttpServletRequest) request;
        System.out.println(t.isUserInRole("uu"));
        System.out.println(t.getUserPrincipal());
//        System.out.println(t.login(););

        HttpSession session =
                ((HttpServletRequest) request).getSession(false);
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            returnError(request, response, "User does not exist in session !");
            chain.doFilter(request, response);
        } else {
            //Get relevant URI.
            String URI = ((HttpServletRequest) request).getRequestURI();

            AuthorizationManager authMgr = new AuthorizationManagerImpl();
            //Invoke AuthorizationManager method to see if user can
            //access resource.
            boolean authorized = authMgr.isUserAuthorized(currentUser, URI);
            if (authorized) {
                chain.doFilter(request, response);
            } else {
                returnError(request, response, "User is not authorized to access this area !");
                chain.doFilter(request, response);
            }
        }
    }

    private void returnError(ServletRequest request, ServletResponse response, String s) {
        System.out.println(s);
    }


    @Override
    public void destroy() {
    }
}
