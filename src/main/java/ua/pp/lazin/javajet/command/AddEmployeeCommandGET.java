package ua.pp.lazin.javajet.command;

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
    private static final String EMPLOYEE_ATTRIBUTE = "employee";
    private static final String KEY_EDIT_MODE = "editMode";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String userIdAsString = request.getParameter("userId");

        if (userIdAsString != null) {
            User user = userService.findByIdWithRoles(Long.valueOf(userIdAsString));
            request.setAttribute(EMPLOYEE_ATTRIBUTE, user);
            request.setAttribute(KEY_EDIT_MODE, true);
        }

        request.setAttribute(POSITIONS_ATTRIBUTE, positionService.findAll());
        request.setAttribute(ROLES_ATTRIBUTE, roleService.findAll());

        return "add-employee";
    }
}
