package ua.pp.lazin.javajet.command;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Ruslan Lazin
 */
public class LogoutCommandGET implements Command {
    private static final Logger logger = Logger.getLogger(LogoutCommandGET.class);
    private static final UserService userService = UserService.getINSTANCE();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        logger.info(request.getUserPrincipal().getName() + " is logging out");
        try {
            request.logout();
            userService.removeCurentUserFromSession(request.getSession());
        } catch (ServletException e) {
            logger.error("Logout fails", e);
        }

        return "redirect:/login";
    }
}

