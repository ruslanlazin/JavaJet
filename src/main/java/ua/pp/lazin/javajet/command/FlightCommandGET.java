package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.persistence.entity.Flight;
import ua.pp.lazin.javajet.service.FlightService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class FlightCommandGET implements Command {
    private static final FlightService flightService = FlightService.getINSTANCE();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long id = Long.valueOf(request.getParameter("flightId"));
        Flight flight = flightService.findById(id);

        List<Flight> flights = new ArrayList<>();
        flights.add(flight);

        request.setAttribute("flights", flights);

        return "flights";
    }
}


