package ua.pp.lazin.javajet.command.main;

import ua.pp.lazin.javajet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class MainRedirectCommandGET implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/common/";
    }
}

