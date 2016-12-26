package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.persistence.dao.RoleDao;
import ua.pp.lazin.javajet.persistence.entity.Role;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;
import ua.pp.lazin.javajet.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class AddEmployeeCommandPOST implements Command {
    // TODO: 25.12.2016 RoleService
    private static final RoleDao roleDao = DaoFactoryCreator.getFactory().getRoleDao();
    private static final UserService userService = UserService.getINSTANCE();
    private static final String ROLES_ATTRIBUTE_NAME = "roles";
    private static final String KEY_USERNAME_ERROR = "wrongusername";
    private static final String KEY_EMAIL_ERROR = "wrongemail";
    private static final String KEY_SUCCESS = "success";
    private static final String EMPLOYEE_ATTRIBUTE_NAME = "employee";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Role> roles = roleDao.findAll();
        request.setAttribute(ROLES_ATTRIBUTE_NAME, roles);


        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstname"));
        user.setSecondName(request.getParameter("secondname"));
        user.setEmail(request.getParameter("email"));
        Role role = roleDao.findByTitle(request.getParameter("role"));
        user.setRole(role);

        if (!userService.isUsernameAvailable(user.getUsername())) {
            request.setAttribute(KEY_USERNAME_ERROR, true);
            request.setAttribute(EMPLOYEE_ATTRIBUTE_NAME, user);
            return "add-employee";
        }
        if (!userService.isEmailAvailable(user.getEmail())) {
            request.setAttribute(KEY_EMAIL_ERROR, true);
            request.setAttribute(EMPLOYEE_ATTRIBUTE_NAME, user);
            return "add-employee";
        }

        userService.create(user);
        request.setAttribute(KEY_SUCCESS, true);
        return "add-employee";
    }
}
