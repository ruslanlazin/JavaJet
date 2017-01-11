package ua.pp.lazin.javajet.util;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.command.EditFlightCommandPOST;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

/**
 * The type Date parser.
 */
public class DateParser {
    private static final Logger logger = Logger.getLogger(DateParser.class);
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    /**
     * Instantiates a new Date parser.
     */
    public DateParser() {
    }

    /**
     * Parse utc date.
     *
     * @param dateAsString the date as string
     * @return the date
     */
    public Date parseUTC(String dateAsString) {

        final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setTimeZone(UTC);
        Date date = null;
        try {
            date = dateFormat.parse(dateAsString);
        } catch (ParseException e) {
            logger.error("An exception occurred during parsing: " + dateAsString, e);
            throw new RuntimeException("An exception occurred during parsing: " + dateAsString, e);
        }
        return date;
    }

    /**
     * Gets now minus hours.
     *
     * @param hours the hours
     * @return the now minus hours
     */
    public static Date getNowMinusHours(int hours) {
        return new Date(new Date().getTime() - (1000 * 60 * 60 * hours));
    }
}