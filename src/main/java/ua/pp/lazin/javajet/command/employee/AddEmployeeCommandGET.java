package ua.pp.lazin.javajet.command.employee;

import ua.pp.lazin.javajet.command.Command;
import ua.pp.lazin.javajet.service.PositionService;
import ua.pp.lazin.javajet.service.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class AddEmployeeCommandGET implements Command {
    private static final String POSITIONS_ATTRIBUTE = "positions";
    private static final String ROLES_ATTRIBUTE = "roles";

    private PositionService positionService = PositionService.getINSTANCE();
    private RoleService roleService = RoleService.getINSTANCE();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute(POSITIONS_ATTRIBUTE, positionService.findAll());
        request.setAttribute(ROLES_ATTRIBUTE, roleService.findAll());

        return "employee";
    }
}
