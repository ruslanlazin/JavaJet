package ua.pp.lazin.javajet.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Set encoding to request
 * Default encoding - UTF-8, can be changed in web.xml,
 * by setting encoding init param
 *
 * @author Ruslan Lazin
 */
@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements javax.servlet.Filter {
    private static final String ENCODING_PARAM = "encoding";
    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter(ENCODING_PARAM);
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
