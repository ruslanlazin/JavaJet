package ua.pp.lazin.javajet.util;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.command.EditFlightCommandPOST;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateParser {
    private static final Logger logger = Logger.getLogger(DateParser.class);
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    public DateParser() {
    }

    public Date parseUTC(String dateAsISO8601String) {

        final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setTimeZone(UTC);
        Date date = null;
        try {
            date = dateFormat.parse(dateAsISO8601String);
        } catch (ParseException e) {
            logger.error("An exception occurred during parsing: " + dateAsISO8601String, e);
            // TODO: 28.12.2016 change
        }
        return date;
    }
}