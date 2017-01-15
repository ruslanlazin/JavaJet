package ua.pp.lazin.javajet.command.employee;

import ua.pp.lazin.javajet.command.Command;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.service.PositionService;
import ua.pp.lazin.javajet.service.RoleService;
import ua.pp.lazin.javajet.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class AddEmployeeCommandGET implements Command {
    private static final PositionService positionService = PositionService.getINSTANCE();
    private static final RoleService roleService = RoleService.getINSTANCE();
    private static final UserService userService = UserService.getINSTANCE();

    private static final String POSITIONS_ATTRIBUTE = "positions";
    private static final String ROLES_ATTRIBUTE = "roles";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String userIdAsString = request.getParameter("userId");

        request.setAttribute(POSITIONS_ATTRIBUTE, positionService.findAll());
        request.setAttribute(ROLES_ATTRIBUTE, roleService.findAll());

        return "employee";
    }
}
