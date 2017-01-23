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
public class EditEmployeeCommandGET implements Command {
    private static final String POSITIONS_ATTRIBUTE = "positions";
    private static final String ROLES_ATTRIBUTE = "roles";
    private static final String USER_ID_ATTRIBUTE = "userId";
    private static final String EMPLOYEE_ATTRIBUTE = "employee";
    private static final String KEY_EDIT_MODE = "editMode";

    private PositionService positionService = PositionService.getINSTANCE();
    private RoleService roleService = RoleService.getINSTANCE();
    private UserService userService = UserService.getINSTANCE();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String userIdAsString = request.getParameter(USER_ID_ATTRIBUTE);

        if (userIdAsString != null) {
            User user = userService.findByIdWithRoles(Long.valueOf(userIdAsString));
            request.setAttribute(EMPLOYEE_ATTRIBUTE, user);
            request.setAttribute(KEY_EDIT_MODE, true);
        }

        request.setAttribute(POSITIONS_ATTRIBUTE, positionService.findAll());
        request.setAttribute(ROLES_ATTRIBUTE, roleService.findAll());

        return "employee";
    }
}
