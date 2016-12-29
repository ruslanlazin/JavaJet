package ua.pp.lazin.javajet.command;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.entity.Aircraft;
import ua.pp.lazin.javajet.persistence.entity.Flight;
import ua.pp.lazin.javajet.service.AircraftService;
import ua.pp.lazin.javajet.service.AirportService;
import ua.pp.lazin.javajet.service.FlightService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Ruslan Lazin
 */
public class AddFlightCommandPOST implements Command {
    private static final Logger logger = Logger.getLogger(AddFlightCommandPOST.class);
    private static final FlightService flightService = FlightService.getINSTANCE();
    private static final AircraftService aircraftService = AircraftService.getINSTANCE();
    private static final AirportService airportService = AirportService.getINSTANCE();

    private static final String AIRCRAFTS_ATTRIBUTE_NAME = "aircrafts";
    private static final String SUCCESS_ATTRIBUTE_NAME = "success";

    private static final String AIRCRAFT_PARAMETER_NAME = "aircraft";
    private static final String FROM_PARAMETER_NAME = "from";
    private static final String TO_PARAMETER_NAME = "to";
    private static final String DEPARTURE_TIME_PARAMETER_NAME = "departureTime";
    private static final String ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm";
    private static final String UTC = "UTC";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Flight flight = new Flight();
        flight.setDepartureTime(parseUTC(request.getParameter(DEPARTURE_TIME_PARAMETER_NAME)));
        flight.setDeparture(airportService.findByCode(request.getParameter(FROM_PARAMETER_NAME)));
        flight.setDestination(airportService.findByCode(request.getParameter(TO_PARAMETER_NAME)));

        Aircraft aircraft = new Aircraft();
        aircraft.setId(Long.valueOf(request.getParameter(AIRCRAFT_PARAMETER_NAME)));
        flight.setAircraft(aircraft);

        flightService.create(flight); // TODO: 29.12.2016 validate

        List<Aircraft> aircrafts = aircraftService.findAll();
        request.setAttribute(AIRCRAFTS_ATTRIBUTE_NAME, aircrafts);
        request.setAttribute(SUCCESS_ATTRIBUTE_NAME, true);

        return "add-flight";
    }

    private Date parseUTC(String dateAsISO8601String) {

        final DateFormat dateFormat = new SimpleDateFormat(ISO_8601_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(UTC));
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
