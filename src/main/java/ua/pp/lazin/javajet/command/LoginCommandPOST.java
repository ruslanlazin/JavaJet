package ua.pp.lazin.javajet.command;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class LoginCommandPOST implements Command {
    private static final Logger logger = Logger.getLogger(LoginCommandPOST.class);
    private static final String USERNAME_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String WRONGLOGIN_ATTRIBUTE = "wronglogin";
    private static final AuthService authService = AuthService.getINSTANCE();

    @Override

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter(USERNAME_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);

        if (authService.login(request.getSession(), username, password)) {
            logger.info(username + " successfully authorized ");
            try {
                request.login(username,password);
            } catch (ServletException e) {
                System.out.println("pipipi");
                e.printStackTrace();
            }
            return "redirect:/";
        }
        request.setAttribute(WRONGLOGIN_ATTRIBUTE, true);
        logger.info(username + " unsuccessfully tried to authorize");
        return "login";
    }
}

