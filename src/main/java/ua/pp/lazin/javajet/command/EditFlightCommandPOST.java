package ua.pp.lazin.javajet.command;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.entity.Aircraft;
import ua.pp.lazin.javajet.persistence.entity.Flight;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.service.AircraftService;
import ua.pp.lazin.javajet.service.AirportService;
import ua.pp.lazin.javajet.service.FlightService;
import ua.pp.lazin.javajet.service.UserService;
import ua.pp.lazin.javajet.util.DateParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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
    private static final String CONCURRENT_MODIFICATION_ATTRIBUTE = "concurrent";
    private static final String FLIGHT_ID_PARAMETER = "flightId";
    private static final String AIRCRAFT_PARAMETER = "aircraft";
    private static final String FROM_PARAMETER = "from";
    private static final String TO_PARAMETER = "to";
    private static final String DEPARTURE_TIME_PARAMETER = "departureTime";
    private static final String VERSION_PARAMETER = "version";
    private static final String CREW_PARAMETER = "crew";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
// TODO: 07.01.2017 remove
        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> stringEntry : map.entrySet()) {
            System.out.println("key" + stringEntry.getKey());

            String[] ar = stringEntry.getValue();
            for (String s : ar) {
                System.out.println(s);
            }
        }

        Aircraft aircraft = new Aircraft();
        aircraft.setId(Long.valueOf(request.getParameter(AIRCRAFT_PARAMETER)));
        Set<User> crew = new HashSet<>();
        String[] crewIdsAsStrings = request.getParameterValues(CREW_PARAMETER);
        if (crewIdsAsStrings != null) {
            for (String userIdAsString : crewIdsAsStrings) {
                crew.add(userService.findById(Long.valueOf(userIdAsString)));
            }
        }
        Flight flight = Flight.newBuilder()
                .id(Long.valueOf(request.getParameter(FLIGHT_ID_PARAMETER)))
                .departureTime(new DateParser().parseUTC(request.getParameter(DEPARTURE_TIME_PARAMETER)))
                .departure(airportService.findByCode(request.getParameter(FROM_PARAMETER)))
                .destination(airportService.findByCode(request.getParameter(TO_PARAMETER)))
                .aircraft(aircraft)
                .crew(crew)
                .version(Integer.valueOf(request.getParameter(VERSION_PARAMETER)))
                .build();


        // TODO: 29.12.2016 validate


        Boolean updateSuccessful = flightService.updateFlightAndCrew(flight);
        if (updateSuccessful) {
            request.setAttribute(SUCCESS_ATTRIBUTE, true);
        } else {
            request.setAttribute(CONCURRENT_MODIFICATION_ATTRIBUTE, true);
            logger.info("2 users tried to edit Flight  "+ flight.getId()+ "simultaneously");
        }

        request.setAttribute("flight", flightService.findByIdWithCrew(flight.getId()));
        request.setAttribute("employees", userService.findAll());
        request.setAttribute(AIRCRAFTS_ATTRIBUTE, aircraftService.findAll());

        return "edit-flight";
    }
}
