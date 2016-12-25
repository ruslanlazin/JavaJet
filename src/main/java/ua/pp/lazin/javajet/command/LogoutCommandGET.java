package ua.pp.lazin.javajet.command;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.service.AuthService;
import ua.pp.lazin.javajet.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class LogoutCommandGET implements Command {
    private static final Logger logger = Logger.getLogger(LogoutCommandGET.class);
    private static final AuthService authService = AuthService.getINSTANCE();
    private static final UserService userService = UserService.getINSTANCE();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        logger.info(userService.getCurrentUser(request).getUsername() + " is logging out");
        authService.logout(request);
        return "login";
    }
}
