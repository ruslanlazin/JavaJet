package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.service.PositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class AddEmployeeCommandGET implements Command {
    private static final PositionService positionService = PositionService.getINSTANCE();
    private static final String POSITIONS_ATTRIBUTE = "positions";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(POSITIONS_ATTRIBUTE, positionService.findAll());
        return "add-employee";
    }
}
