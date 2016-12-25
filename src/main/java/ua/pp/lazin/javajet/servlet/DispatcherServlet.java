package ua.pp.lazin.javajet.servlet;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.command.Command;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DispatcherServlet class is a controller. Use MVC pattern.
 * There is only servlet on the project.
 *
 * @author Ruslan Lazin
 */
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private static final String ERROR_MESSAGE_ATTRIBUTE_NAME = "message";
    private static final String ERROR_PAGE = "error";
    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/pages/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";
    private static final String REDIRECT_PREFIX = "redirect:";
    private static final String REDIRECT_DELIMITER = ":";


    /**
     * logger use Log4j library. @see (http://logging.apache.org/log4j/)
     */
    private static final Logger logger =
            Logger.getLogger(DispatcherServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
    }


    public void init(ServletConfig config) throws ServletException {
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        performTask(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        performTask(request, response);
    }


    /**
     * performTask execute command from request.
     * And pass work to Command classes.
     *
     * @param request  from user.
     * @param response by server.
     * @see CommandResolver
     */
    private void performTask(HttpServletRequest request,
                             HttpServletResponse response) {

        Command command = CommandResolver.getCommand(request);
        if (command == null) {
            logger.info("Incorrect request path " + request.getRequestURI());
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE_NAME, "Requested path is incorrect");
            // TODO: 25.12.2016 add error message
            forward(ERROR_PAGE, request, response);
            return;
        }
        String viewName = null;
        try {
            viewName = command.execute(request, response);
            logger.debug("command is: " + command + "viewName is: " + viewName);
        } catch (Exception e) {
            logger.error("An exception occurred during command " + command + " executing", e);
            // TODO: 25.12.2016 add error message
            forward(ERROR_PAGE, request, response);
            return;
        }
        if (viewName.startsWith(REDIRECT_PREFIX)) {
            redirect(viewName, request, response);
        } else {
            forward(viewName, request, response);
        }
    }


    private void redirect(String target, HttpServletRequest request, HttpServletResponse response) {
        target = target.split(REDIRECT_DELIMITER)[1];
        try {
            response.sendRedirect(request.getContextPath() + target);
        } catch (IOException e) {
            logger.error("An error occurred during redirecting to " + target, e);
        }
    }

    private void forward(String target, HttpServletRequest request, HttpServletResponse response) {
        target = String.format(VIEW_RESOLVER_PREFIX + "%s" + VIEW_RESOLVER_SUFFIX, target);
        try {
            request.getRequestDispatcher(target).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("An error occurred during page " + target + " rendering", e);
        }
    }
}
