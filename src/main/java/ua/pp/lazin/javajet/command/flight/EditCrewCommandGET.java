package ua.pp.lazin.javajet.command.flight;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.command.Command;
import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.service.AircraftService;
import ua.pp.lazin.javajet.service.FlightService;
import ua.pp.lazin.javajet.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ruslan Lazin
 */
public class EditCrewCommandGET implements Command {
    private static final FlightService flightService = FlightService.getINSTANCE();
    private static final UserService userService = UserService.getINSTANCE();

    private static final String FLIGHT_ATTRIBUTE = "flight";
    private static final String EMPLOYEES_ATTRIBUTE = "employees";
    private static final String FLIGHT_ID_PARAMETER = "flightId";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Flight flight = flightService.findById(Long.valueOf(request.getParameter(FLIGHT_ID_PARAMETER)));
        request.setAttribute(FLIGHT_ATTRIBUTE, flightService.findByIdWithCrewAirports(flight.getId()));
        request.setAttribute(EMPLOYEES_ATTRIBUTE, userService.findAllWorkingAirCrewMembers());

        return "edit-crew";
    }
}


