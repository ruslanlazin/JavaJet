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
 * DispatcherServlet is a Front controller.
 * It's only servlet on the project.
 *
 * @author Ruslan Lazin
 * @see HttpServlet#HttpServlet() HttpServlet#HttpServlet()
 */
@WebServlet
public class DispatcherServlet extends HttpServlet {
    private static final String ERROR_MESSAGE_ATTRIBUTE = "message";
    private static final String ERROR_PAGE = "error";
    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/pages/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";
    private static final String REDIRECT_PREFIX = "redirect:";
    private static final String REDIRECT_DELIMITER = ":";
    private static final Logger logger = Logger.getLogger(DispatcherServlet.class);

    /**
     * Instantiates a new Dispatcher servlet.
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
     * performTask execute command  based on request path.
     *
     * @param request  from user.
     * @param response by server.
     * @see CommandResolver
     */
    private void performTask(HttpServletRequest request,
                             HttpServletResponse response) {

        Command command = CommandResolver.getCommand(request);
        if (command == null) {
            logger.info("Incorrect request path " + request.getRequestURI() + request.getMethod());
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Requested path doesn't exist");
            forward(ERROR_PAGE, request, response);
            return;
        }
        String viewName = null;
        try {
            viewName = command.execute(request, response);
            logger.debug("command: " + command + " viewName: " + viewName);
        } catch (Exception e) {
            logger.error("An exception occurred during command " + command + " executing", e);
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Internal server error has occurred. " +
                    "See server log for detail");
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
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Internal server error has occurred. " +
                    "See server log for detail");
            forward(ERROR_PAGE, request, response);
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
