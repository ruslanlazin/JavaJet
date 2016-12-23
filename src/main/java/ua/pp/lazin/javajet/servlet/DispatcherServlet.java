package ua.pp.lazin.javajet.servlet;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DispatcherServlet class is a controller. Use MVC pattern.
 * This is only servlet of project.
 *
 * @author Ruslan Lazin
 */
@WebServlet("/main")
public class DispatcherServlet extends HttpServlet {

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


    /**
     * init method read init data from web.xml
     */
    public void init(ServletConfig config) throws ServletException {
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        logger.debug("GET");
        logger.debug("Topic: " + request.getQueryString());


        performTask(request, response);
    }

    /**
     * POST used only when user send big(create page, load image)
     * or security(login password) data!
     *
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
//    protected void doPost(HttpServletRequest request,
//                          HttpServletResponse response) throws ServletException, IOException {
//
//        logger.debug("POST");
//
////        initialSetup(request);
//
//        //Only save, send security or big data!
////        performTaskPostMethod(request, response);
//    }


    /**
     * for GET method.
     * <p>
     * performTask execute command from request.
     * And pass work to Command classes.
     *
     * @param request  from user.
     * @param response by server.
     */
    private void performTask(HttpServletRequest request,
                             HttpServletResponse response) {

        String page = null;

        try {
            Command command = CommandResolver.getCommand(request);
            logger.debug("command is: " + command.getClass().getName());

            page = command.execute(request, response);
            logger.debug("page is: " + page);
        } catch (Exception e) {
            logger.error("exception when try execute command: " + e);
        }
        forward(page, request, response);
//
//        if (null != page) {
//            //Ok. We get page from Command class
//            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
//
//            try {
//                dispatcher.forward(request, response);
//            } catch (ServletException e) {
//                logger.error("Servlet execption: " + e);
//            } catch (IOException e) {
//                logger.error("IO execption: " + e);
//            }
//        } else {
//            logger.error("page is null!");
//
//            //redirect to main page:
////            PageManager pageManager = PageManager.getInstance();
////            page = pageManager.getPage(PageEnum.MAIN_PAGE_PATH);
//            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
//
//            try {
//                dispatcher.forward(request, response);
//            } catch (ServletException e) {
//                logger.error("Servlet execption: " + e);
//            } catch (IOException e) {
//                logger.error("IO execption: " + e);
//            }
//
//            logger.debug("user is served");
//            return;
//        }
    }


    /**
     * for POST method.
     * <p>
     * performTask execute command from request.
     * And pass work to CommandPost classes.
     *
     * @param request  from user.
     * @param response by server.
     */
//    private void performTaskPostMethod(HttpServletRequest request,
//                                       HttpServletResponse response) {
//
//        String page = null;
//
//        try {
//            CommandPost command = CommandPostFactory.getCommand(request);
//            logger.debug("command is: " + command.getClass().getName());
//
//            page = command.executePost(request);
//            logger.debug("page is: " + page);
//        } catch (Exception e) {
//            logger.error("exception when try execute command: " + e);
//        }
//
//
//        if (null != page) {
//            //Ok. We get page from CommandPost class
//
//            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
//
//            try {
//                dispatcher.forward(request, response);
//            } catch (ServletException e) {
//                logger.error("Servlet execption: " + e);
//            } catch (IOException e) {
//                logger.error("IO execption: " + e);
//            }
//        } else {
//            logger.error("page is null!");
//
//            //redirect to main page:
////            PageManager pageManager = PageManager.getInstance();
////            page = pageManager.getPage(PageEnum.MAIN_PAGE_PATH);
//            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
//
//            try {
//                dispatcher.forward(request, response);
//            } catch (ServletException e) {
//                logger.error("Servlet execption: " + e);
//            } catch (IOException e) {
//                logger.error("IO execption: " + e);
//            }
//
//            logger.debug("user is served");
//            return;
//        }
//    }
//


    private void forward(String target, HttpServletRequest request, HttpServletResponse response) {
        target = String.format("/WEB-INF/pages/%s.jsp", target);
        try {
             request.getRequestDispatcher(target).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("An error occurred during page rendering", e);
        }
    }
}
