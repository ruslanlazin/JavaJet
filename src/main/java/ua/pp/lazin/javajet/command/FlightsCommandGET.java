package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.persistence.entity.Flight;
import ua.pp.lazin.javajet.service.FlightService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class FlightsCommandGET implements Command {
    private static final FlightService flightService = FlightService.getINSTANCE();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Flight> flights = flightService.findAll();
        request.setAttribute("flights", flights);

        return "flights";
    }
}


