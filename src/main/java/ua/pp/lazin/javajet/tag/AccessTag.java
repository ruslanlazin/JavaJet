package ua.pp.lazin.javajet.tag;

import org.apache.log4j.Logger;
import sun.util.calendar.ZoneInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Tag displays body context only if <tt>request.isUserInRole()</tt>
 * for attribute "role" evaluates to true
 *
 * @author Ruslan Lazin
 */
public class AccessTag extends TagSupport {

    private String role;

    /**
     * Instantiates a new Access tag.
     */
    public AccessTag() {
        this.init();
    }

    private void init() {
        this.role = null;
    }

    // Lifecycle management and implementation of conditional behavior

    /**
     * Includes its body if <tt>condition()</tt> evaluates to true.
     */
    public int doStartTag() throws JspException {

        // execute our condition() method once per invocation
        boolean result = condition();

        // handle conditional behavior
        if (result)
            return EVAL_BODY_INCLUDE;
        else
            return SKIP_BODY;
    }

    /**
     *
     * @return true if <tt>request.isUserInRole(role)</tt> returns true
     */
    private boolean condition() {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        return request.isUserInRole(role);
    }


    public void release() {
        this.init();
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
