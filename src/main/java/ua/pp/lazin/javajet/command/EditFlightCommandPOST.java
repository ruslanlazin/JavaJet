package ua.pp.lazin.javajet.command;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.entity.Aircraft;
import ua.pp.lazin.javajet.persistence.entity.Flight;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.service.AircraftService;
import ua.pp.lazin.javajet.service.AirportService;
import ua.pp.lazin.javajet.service.FlightService;
import ua.pp.lazin.javajet.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Ruslan Lazin
 */
public class EditFlightCommandPOST implements Command {
    private static final Logger logger = Logger.getLogger(EditFlightCommandPOST.class);
    private static final FlightService flightService = FlightService.getINSTANCE();
    private static final AircraftService aircraftService = AircraftService.getINSTANCE();
    private static final AirportService airportService = AirportService.getINSTANCE();
    private static final UserService userService = UserService.getINSTANCE();

    private static final String AIRCRAFTS_ATTRIBUTE = "aircrafts";
    private static final String SUCCESS_ATTRIBUTE = "success";
    private static final String FLIGHT_ID_PARAMETER = "flightId";
    private static final String AIRCRAFT_PARAMETER = "aircraft";
    private static final String FROM_PARAMETER = "from";
    private static final String TO_PARAMETER = "to";
    private static final String DEPARTURE_TIME_PARAMETER = "departureTime";
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    private static final String UTC = "UTC";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Flight flight = new Flight();
        flight.setId(Long.valueOf(request.getParameter(FLIGHT_ID_PARAMETER)));
        flight.setDepartureTime(parseUTC(request.getParameter(DEPARTURE_TIME_PARAMETER)));
        flight.setDeparture(airportService.findByCode(request.getParameter(FROM_PARAMETER)));
        flight.setDestination(airportService.findByCode(request.getParameter(TO_PARAMETER)));

        Aircraft aircraft = new Aircraft();
        aircraft.setId(Long.valueOf(request.getParameter(AIRCRAFT_PARAMETER)));
        flight.setAircraft(aircraft);
        // TODO: 29.12.2016 validate

        List<User> crew = new ArrayList<>();
        String[] crewIdsAsStrings = request.getParameterValues("crew");
        if (crewIdsAsStrings != null) {
            for (String userIdAsString : crewIdsAsStrings) {
                crew.add(userService.findById(Long.valueOf(userIdAsString)));
            }
        }
        flight.setCrew(crew);
        System.out.println(flight);

        flightService.updateFlightAndCrew(flight);

        request.setAttribute("flight", flight);
        request.setAttribute("employees", userService.findAll());
        request.setAttribute(AIRCRAFTS_ATTRIBUTE, aircraftService.findAll());
        request.setAttribute(SUCCESS_ATTRIBUTE, true);

        return "edit-flight";
    }

    private Date parseUTC(String dateAsISO8601String) {

        final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
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
