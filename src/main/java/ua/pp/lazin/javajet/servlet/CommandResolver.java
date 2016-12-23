package ua.pp.lazin.javajet.servlet;

import ua.pp.lazin.javajet.command.Command;
import ua.pp.lazin.javajet.command.LoginCommandGET;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ruslan Lazin
 */
class CommandResolver {

    private static final Map<String, Command> mappingGET;
    private static final Map<String, Command> mappingPOST;

    static {
        /*Initialize  application's GET paths
        * @see GETMapping.properties
        * @see PropertiesLoader*/
        mappingGET =new HashMap<>();
        mappingGET.put("/login",new LoginCommandGET());


        mappingPOST = new HashMap<>();
    }






    static Command getCommand(HttpServletRequest request) {
        System.out.println("getComm");
        System.out.println(request.getMethod());
        System.out.println(request.getRequestURI());

        return mappingGET.get(request.getRequestURI());
    }
}
