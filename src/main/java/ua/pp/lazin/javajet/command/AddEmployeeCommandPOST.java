package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.persistence.dao.PositionDao;
import ua.pp.lazin.javajet.persistence.entity.Position;
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
    private static final PositionDao POSITION_DAO = DaoFactoryCreator.getFactory().getRoleDao();
    private static final UserService userService = UserService.getINSTANCE();
    private static final String ROLES_ATTRIBUTE = "roles";
    private static final String KEY_USERNAME_ERROR = "wrongusername";
    private static final String KEY_EMAIL_ERROR = "wrongemail";
    private static final String KEY_SUCCESS = "success";
    private static final String EMPLOYEE_ATTRIBUTE = "employee";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Position> positions = POSITION_DAO.findAll();
        request.setAttribute(ROLES_ATTRIBUTE, positions);

        // TODO: 06.01.2017 move
        Position position = POSITION_DAO.findByTitle(request.getParameter("position"));

        User user = User.newBuilder()
                .username(request.getParameter("username"))
                .password(request.getParameter("password"))
                .firstName(request.getParameter("firstname"))
                .secondName(request.getParameter("secondname"))
                .email(request.getParameter("email"))
                .position(position)
                .build();

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
