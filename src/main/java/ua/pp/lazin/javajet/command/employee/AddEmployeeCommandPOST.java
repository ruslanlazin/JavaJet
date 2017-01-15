package ua.pp.lazin.javajet.command.employee;

import ua.pp.lazin.javajet.command.Command;
import ua.pp.lazin.javajet.entity.Position;
import ua.pp.lazin.javajet.entity.Role;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.service.PositionService;
import ua.pp.lazin.javajet.service.RoleService;
import ua.pp.lazin.javajet.service.UserService;
import ua.pp.lazin.javajet.util.EntityParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ruslan Lazin
 */
public class AddEmployeeCommandPOST implements Command {
    private static final PositionService positionService = PositionService.getINSTANCE();
    private static final UserService userService = UserService.getINSTANCE();
    private static final RoleService roleService = RoleService.getINSTANCE();
    private static final String KEY_USERNAME_ERROR = "wrongusername";
    private static final String KEY_EMAIL_ERROR = "wrongemail";
    private static final String KEY_SUCCESS = "success";
    private static final String EMPLOYEE_ATTRIBUTE = "employee";
    private static final String ALL_POSITIONS_ATTRIBUTE = "positions";
    private static final String ALL_ROLES_ATTRIBUTE = "roles";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = EntityParser.parseUserWithRoles(request);

        boolean usernameAvailable = userService.isUsernameAvailable(user.getUsername());
        boolean emailAvailable = userService.isEmailAvailable(user.getEmail());

        if (usernameAvailable && emailAvailable) {
            userService.create(user);
            request.setAttribute(KEY_SUCCESS, true);

        } else {
            request.setAttribute(KEY_EMAIL_ERROR, !emailAvailable);
            request.setAttribute(KEY_USERNAME_ERROR, !usernameAvailable);
            request.setAttribute(EMPLOYEE_ATTRIBUTE, user);
        }

        request.setAttribute(ALL_POSITIONS_ATTRIBUTE, positionService.findAll());
        request.setAttribute(ALL_ROLES_ATTRIBUTE, roleService.findAll());

        return "employee";
    }
}


