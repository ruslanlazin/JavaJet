package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.service.AircraftService;
import ua.pp.lazin.javajet.service.FlightService;
import ua.pp.lazin.javajet.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class FlightCommandGET implements Command {
    private static final FlightService flightService = FlightService.getINSTANCE();
    private static final AircraftService aircraftService = AircraftService.getINSTANCE();
    private static final UserService userService = UserService.getINSTANCE();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long id = Long.valueOf(request.getParameter("flightId"));
        request.setAttribute("flight", flightService.findByIdWithCrewAirports(id));
        request.setAttribute("employees", flightService.findByIdWithCrew(id).getCrew());
        request.setAttribute("aircrafts", aircraftService.findAll());

        return "f2";
    }
}


