package ua.pp.lazin.javajet.command;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class LoginCommandPOST implements Command {
    private static final Logger logger = Logger.getLogger(LoginCommandPOST.class);
    private static final String USERNAME_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter(USERNAME_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);

        AuthService authService = new AuthService();

        if (authService.login(request, username, password)) {
            return "redirect:/";
        }


        return "login";
    }
}

