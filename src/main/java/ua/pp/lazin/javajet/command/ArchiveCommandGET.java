package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.service.FlightService;
import ua.pp.lazin.javajet.util.DateParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Ruslan Lazin
 */
public class ArchiveCommandGET implements Command {
    private static final FlightService flightService = FlightService.getINSTANCE();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        //retrieves all archived flights
        request.setAttribute("flights", flightService.findAllBeforeThen(new Date()));

        return "archive";
    }
}


