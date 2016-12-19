package ua.pp.lazin.javajet.filters;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.ConnectionManager;
import ua.pp.lazin.javajet.persistence.factory.FactoryCreator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;


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


        FactoryCreator.getFactory();
        ConnectionManager.getConnection();


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
