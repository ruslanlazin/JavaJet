package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.service.AircraftService;
import ua.pp.lazin.javajet.service.FlightService;
import ua.pp.lazin.javajet.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class EditFlightCommandGET implements Command {
    private static final FlightService flightService = FlightService.getINSTANCE();
    private static final AircraftService aircraftService = AircraftService.getINSTANCE();
    private static final String FLIGHT_ID_PARAMETER = "flightId";
    private static final String AIRCRAFTS_ATTRIBUTE = "aircrafts";
    private static final String FLIGHT_ATTRIBUTE = "flight";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String flightIdAsString = request.getParameter(FLIGHT_ID_PARAMETER);
        if (flightIdAsString != null) {
            Long id = Long.valueOf(flightIdAsString);
            request.setAttribute(FLIGHT_ATTRIBUTE, flightService.findByIdWithCrew(id));
        }
        request.setAttribute(AIRCRAFTS_ATTRIBUTE, aircraftService.findAll());

        return "edit-flight";
    }
}


