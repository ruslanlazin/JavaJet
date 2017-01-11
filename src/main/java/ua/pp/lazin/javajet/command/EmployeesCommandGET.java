package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class EmployeesCommandGET implements Command {
    private static final UserService userService = UserService.getINSTANCE();
    private static final String EMPLOYEES_ATTRIBUTE = "employees";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userService.findAll();
        request.setAttribute(EMPLOYEES_ATTRIBUTE,users);
        return "employees";
    }
}

