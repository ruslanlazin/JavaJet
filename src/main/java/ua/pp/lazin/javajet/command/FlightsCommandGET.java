package ua.pp.lazin.javajet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class FlightsCommandGET implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "flights";
    }
}
