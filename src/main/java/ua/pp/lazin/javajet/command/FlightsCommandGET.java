package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.service.FlightService;
import ua.pp.lazin.javajet.util.DateParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class FlightsCommandGET implements Command {
    private static final FlightService flightService = FlightService.getINSTANCE();
private static final int HOURS_TO_MINUS = 12;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        //retrieves all planned flights and flights started last 12 hours
                request.setAttribute("flights", flightService
                .findAllLaterThen(DateParser.getNowMinusHours(HOURS_TO_MINUS)));

        return "flights";
    }
}


