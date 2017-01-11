package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.entity.Aircraft;
import ua.pp.lazin.javajet.service.AircraftService;
import ua.pp.lazin.javajet.service.FlightService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class AddFlightCommandGET implements Command {
    private static final FlightService flightService = FlightService.getINSTANCE();
    private static final AircraftService aircraftService = AircraftService.getINSTANCE();
    private static final String AIRCRAFTS_ATTRIBUTE = "aircrafts";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Aircraft> aircrafts = aircraftService.findAll();
        request.setAttribute(AIRCRAFTS_ATTRIBUTE, aircrafts);
        return "edit-flight";
    }
}
