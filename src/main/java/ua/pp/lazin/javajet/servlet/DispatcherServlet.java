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
    private static final String MESSAGE_ATTRIBUTE_NAME = "message";
    private static final String ERROR_PAGE = "error";
    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/pages/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";
    private static final String REDIRECT_PREFIX = "redirect:";
    private static final String REDIRECT_DELIMITER = ":";


    /**
     * logger use Log4j library. @see (http://logging.apache.org/log4j/)
     */
    private static Logger logger =
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

        Command command = null;
        String pageName = null;
        try {
            command = CommandResolver.getCommand(request);
            logger.debug("command is: " + command.getClass().getName());

            pageName = command.execute(request, response);
            logger.debug("pageName is: " + pageName);
        } catch (Exception e) {
            logger.error("exception when try execute command " + command, e);
        }

        if (pageName == null) {
            request.setAttribute(MESSAGE_ATTRIBUTE_NAME, "Requested path is incorrect");
            forward(ERROR_PAGE, request, response);
            return;
        }
        if (pageName.startsWith(REDIRECT_PREFIX)) {
            redirect(pageName, request, response);
        } else {
            forward(pageName, request, response);
        }
    }


    private void redirect(String target, HttpServletRequest request, HttpServletResponse response) {
        target = target.split(REDIRECT_DELIMITER)[1];
        try {
            response.sendRedirect(target);
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
