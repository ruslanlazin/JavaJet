package ua.pp.lazin.javajet.command.flight;

import ua.pp.lazin.javajet.command.Command;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.service.FlightService;
import ua.pp.lazin.javajet.service.UserService;
import ua.pp.lazin.javajet.util.DateParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class MyScheduleCommandGET implements Command {
    private static final FlightService flightService = FlightService.getINSTANCE();
    private static final UserService userService = UserService.getINSTANCE();
    private static final int HOURS_TO_MINUS = 12;
    private static final String FLIGHTS_ATTRIBUTE = "flights";
    private static final String KEY_MY_MODE = "myMode";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User currentUser = userService.getCurrentUser(request.getSession());

        //retrieves all planned flights and flights started last 12 hours
        request.setAttribute(FLIGHTS_ATTRIBUTE, flightService.getAllUsersFlightsLaterThen
                (currentUser, DateParser.getNowMinusHours(HOURS_TO_MINUS)));

        request.setAttribute(KEY_MY_MODE, true);
        return "schedule";
    }
}


