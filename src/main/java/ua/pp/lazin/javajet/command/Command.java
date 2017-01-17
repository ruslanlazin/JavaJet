package ua.pp.lazin.javajet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface Command.
 *
 * @author Ruslan Lazin
 */
public interface Command {
    /**
     * Execute string.
     *
     * @param request  the request
     * @param response the response
     * @return the string meaning view name without suffix and prefix
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
