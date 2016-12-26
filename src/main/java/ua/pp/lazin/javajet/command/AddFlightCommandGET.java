package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.persistence.dao.RoleDao;
import ua.pp.lazin.javajet.persistence.entity.Airport;
import ua.pp.lazin.javajet.persistence.entity.Role;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;
import ua.pp.lazin.javajet.service.AirportService;
import ua.pp.lazin.javajet.service.FlightService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class AddFlightCommandGET implements Command {
    private static final RoleDao roleDao = DaoFactoryCreator.getFactory().getRoleDao();
    private static final FlightService flightService = FlightService.getINSTANCE();
    private static final AirportService airportService = AirportService.getINSTANCE();
    private static final String ROLES_ATTRIBUTE_NAME = "roles";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Role> roles = roleDao.findAll();
        request.setAttribute(ROLES_ATTRIBUTE_NAME, roles);

        List<Airport>  airports = airportService.findAll();
        request.setAttribute("airports",airports);

        return "add-flight";
    }
}
