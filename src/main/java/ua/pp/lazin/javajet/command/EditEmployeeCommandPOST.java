package ua.pp.lazin.javajet.command;

import org.apache.log4j.Logger;
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
public class EditEmployeeCommandPOST implements Command {
    private static final PositionService positionService = PositionService.getINSTANCE();
    private static final UserService userService = UserService.getINSTANCE();
    private static final RoleService roleService = RoleService.getINSTANCE();
    private static final Logger logger = Logger.getLogger(EditEmployeeCommandPOST.class);
    private static final String KEY_USERNAME_ERROR = "wrongusername";
    private static final String KEY_EMAIL_ERROR = "wrongemail";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_EDIT_MODE = "editMode";
    private static final String KEY_CONCURRENT_MODIFICATION = "concurrent";
    private static final String ALL_POSITIONS_ATTRIBUTE = "positions";
    private static final String EMPLOYEE_ATTRIBUTE = "employee";
    private static final String ALL_ROLES_ATTRIBUTE = "roles";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = EntityParser.parseUserWithRoles(request);
        User userFromDB = userService.findById(user.getId());

        //check if username available
        boolean usernameAvailable;
        if (user.getUsername().equals(userFromDB.getUsername())) {
            usernameAvailable = true;
        } else {
            usernameAvailable = userService.isUsernameAvailable(user.getUsername());
            request.setAttribute(KEY_USERNAME_ERROR, !usernameAvailable);
        }
        //check if email available
        boolean emailAvailable;
        if (user.getEmail().equals(userFromDB.getEmail())) {
            emailAvailable = true;
        } else {
            emailAvailable = userService.isEmailAvailable(user.getEmail());
            request.setAttribute(KEY_EMAIL_ERROR, !emailAvailable);
        }
        //if username and email are OK, trying to save changes to DB
        if (usernameAvailable && emailAvailable) {
            boolean isUpdateSuccessful = userService.updateWithRoles(user);
            user = userService.findByIdWithRoles(user.getId());
            if (isUpdateSuccessful) {
                request.setAttribute(KEY_SUCCESS, true);
            } else {
                request.setAttribute(KEY_CONCURRENT_MODIFICATION, true);
                logger.info("Two or more users tried to edit User " + user.getUsername() +
                        " simultaneously");
            }
        }

        request.setAttribute(EMPLOYEE_ATTRIBUTE, user);
        request.setAttribute(ALL_POSITIONS_ATTRIBUTE, positionService.findAll());
        request.setAttribute(ALL_ROLES_ATTRIBUTE, roleService.findAll());
        request.setAttribute(KEY_EDIT_MODE, true);

        return "employee";
    }
}


