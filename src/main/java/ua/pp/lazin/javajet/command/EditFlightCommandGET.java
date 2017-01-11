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
    private static final UserService userService = UserService.getINSTANCE();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String flightIdAsString = request.getParameter("flightId");
        if (flightIdAsString != null) {
            Long id = Long.valueOf(flightIdAsString);
            request.setAttribute("flight", flightService.findByIdWithCrew(id));
        }
        request.setAttribute("aircrafts", aircraftService.findAll());

        return "edit-flight";
    }
}


