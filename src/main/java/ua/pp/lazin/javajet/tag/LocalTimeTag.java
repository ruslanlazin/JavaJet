package ua.pp.lazin.javajet.tag;

import org.apache.log4j.Logger;
import sun.util.calendar.ZoneInfo;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Tag provides local time exposing by applying TimeZone attribute to
 * java.util.Date.
 * Exposing "N/A" if there is no correct TimeZone in TimeZone attribute.
 *
 * @author Ruslan Lazin
 */
public class LocalTimeTag extends TagSupport {
    private static final Logger logger = Logger.getLogger(LocalTimeTag.class);
    private static final String LOCAL_TIME_NOT_AVAILABLE = "N/A";

    protected Date value;
    private String pattern;

    private Object timeZone;
    private Object locale;

    public LocalTimeTag() {
        this.init();
    }

    private void init() {
        this.pattern = null;
        this.value = null;
        this.timeZone = null;
        this.locale = null;
    }

    public int doEndTag() throws JspException {

        if (this.value == null) {
            return 6;
        }
        String formattedOutput;

        // Trying to resolve the TimeZone
        TimeZone tz = readTimeZone();
        // If there is not TimeZone specified - print "N/A"
        if (tz == null) {
            formattedOutput = LOCAL_TIME_NOT_AVAILABLE;
        } else {                           //if there is correct TimeZone provided
            Locale locale = readLocale();
            DateFormat dateFormat = buildDateFormat(tz, locale);
            formattedOutput = dateFormat.format(this.value);
        }

        try {
            this.pageContext.getOut().print(formattedOutput);
        } catch (IOException e) {
            logger.error("An exception occurred during " + formattedOutput + " printing", e);
            throw new JspTagException(e.toString(), e);
        }
        return 6;
    }

    private DateFormat buildDateFormat(TimeZone tz, Locale locale) {
        DateFormat dateFormat;
        if (pattern != null) {
            if (this.locale != null) {
                dateFormat = new SimpleDateFormat(this.pattern, locale);
            } else {
                dateFormat = new SimpleDateFormat(this.pattern);
            }
        } else {
            dateFormat = new SimpleDateFormat();
        }
        dateFormat.setTimeZone(tz);
        return dateFormat;
    }

    private Locale readLocale() throws JspTagException {
        Locale locale = null;
        if (this.locale instanceof String && ((String) this.locale).equals("")) {
            this.locale = null;
        }
        if (this.locale != null) {
            if (this.locale instanceof String) {
                locale = Locale.forLanguageTag((String) this.locale);
            } else {
                if (!(this.locale instanceof Locale)) {
                    logger.error("An instance of " + this.locale.getClass() +
                            "has been given instead of Locale");
                    throw new JspTagException("Incorrect Locale instance");
                }
                locale = (Locale) this.locale;
            }
        } else {
            locale = Locale.getDefault();
        }
        return locale;
    }

    private TimeZone readTimeZone() throws JspTagException {
        TimeZone tz = null;
        if (this.timeZone instanceof String && ((String) this.timeZone).equals("")) {
            this.timeZone = null;
        }
        if (this.timeZone != null) {
            if (this.timeZone instanceof String) {
                tz = ZoneInfo.getTimeZone((String) this.timeZone);
            } else {
                if (!(this.timeZone instanceof TimeZone)) {
                    logger.error("An instance of " + this.timeZone.getClass() +
                            "has been given instead of TimeZone");
                    throw new JspTagException("Incorrect TimeZone instance");
                }
                tz = (TimeZone) this.timeZone;
            }
        }
        return tz;
    }

    public void release() {
        this.init();
    }

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Object getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(Object timeZone) {
        this.timeZone = timeZone;
    }

    public Object getLocale() {
        return locale;
    }

    public void setLocale(Object locale) {
        this.locale = locale;
    }
}
