package ua.pp.lazin.javajet.servlet;

import ua.pp.lazin.javajet.command.Command;
import ua.pp.lazin.javajet.command.LoginCommandGET;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ruslan Lazin
 */
public class CommandResolver {
    public static Command getCommand(HttpServletRequest request) {
        return new LoginCommandGET();
    }
}
