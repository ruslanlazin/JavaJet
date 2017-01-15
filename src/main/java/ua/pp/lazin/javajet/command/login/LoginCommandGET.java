package ua.pp.lazin.javajet.command.login;

import ua.pp.lazin.javajet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class LoginCommandGET implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }
}

