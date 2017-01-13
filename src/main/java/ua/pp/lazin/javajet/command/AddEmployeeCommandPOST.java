package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.entity.Position;
import ua.pp.lazin.javajet.entity.Role;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.service.PositionService;
import ua.pp.lazin.javajet.service.RoleService;
import ua.pp.lazin.javajet.service.UserService;

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
    private static final String ALL_POSITIONS_ATTRIBUTE = "positions";
    private static final String KEY_USERNAME_ERROR = "wrongusername";
    private static final String KEY_EMAIL_ERROR = "wrongemail";
    private static final String KEY_SUCCESS = "success";
    private static final String EMPLOYEE_ATTRIBUTE = "employee";
    private static final String ALL_ROLES_ATTRIBUTE = "roles";
    private static final String USER_ROLES_PARAMETER = "userRoles";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = User.newBuilder()
                .username(request.getParameter("username"))
                .password(request.getParameter("password"))
                .firstName(request.getParameter("firstname"))
                .secondName(request.getParameter("secondname"))
                .email(request.getParameter("email"))
//                .position(positionService.findByTitle(request.getParameter("position")))
                .position(Position.newBuilder().id(Long.valueOf(request.getParameter("position"))).build())
                .working(Boolean.valueOf(request.getParameter("working")))
                .build();

        String[] rolesIdAsStrings = request.getParameterValues(USER_ROLES_PARAMETER);
        if (rolesIdAsStrings != null) {
            Set<Role> userRoles = new HashSet<>(rolesIdAsStrings.length);
            for (String roleIdAsString : rolesIdAsStrings) {
//            userRoles.add(roleService.findById(Long.valueOf(roleIdAsString)));
                userRoles.add(Role.newBuilder().id(Long.valueOf(roleIdAsString)).build());
            }
            user.setRoles(userRoles);
        }


        request.setAttribute(ALL_POSITIONS_ATTRIBUTE, positionService.findAll());
        request.setAttribute(ALL_ROLES_ATTRIBUTE, roleService.findAll());

        if (!userService.isUsernameAvailable(user.getUsername())) {
            request.setAttribute(KEY_USERNAME_ERROR, true);
            request.setAttribute(EMPLOYEE_ATTRIBUTE, user);
            return "add-employee";
        }
        if (!userService.isEmailAvailable(user.getEmail())) {
            request.setAttribute(KEY_EMAIL_ERROR, true);
            request.setAttribute(EMPLOYEE_ATTRIBUTE, user);
            return "add-employee";
        }

        userService.create(user);
        request.setAttribute(KEY_SUCCESS, true);
        return "add-employee";
    }
}
