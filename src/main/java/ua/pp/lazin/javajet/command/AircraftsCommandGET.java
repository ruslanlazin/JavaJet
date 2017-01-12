package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.service.AircraftService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class AircraftsCommandGET implements Command {
    private static final AircraftService aircraftService = AircraftService.getINSTANCE();
    private static final String AIRCRAFTS_ATTRIBUTE = "aircrafts";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute(AIRCRAFTS_ATTRIBUTE, aircraftService.findAll());
        return "aircrafts";
    }
}

