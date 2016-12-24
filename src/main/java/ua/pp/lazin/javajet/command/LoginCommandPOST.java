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
    private static final String WRONGLOGIN_ATTRIBUTE_NAME = "wronglogin";
    private static final AuthService authService = new AuthService();

    @Override

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter(USERNAME_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);

        if (authService.login(request, username, password)) {
            logger.info(username + " successfully authorized ");
            return "redirect:/";
        }
        request.setAttribute(WRONGLOGIN_ATTRIBUTE_NAME, true);
        logger.info(username + " unsuccessfully tried to authorize");
        return "login";
    }
}

