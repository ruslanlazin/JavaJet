package ua.pp.lazin.javajet.servlet;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.command.Command;
import ua.pp.lazin.javajet.util.PropertiesLoader;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Class encapsulates the logic
 * of choosing appropriate Command object for each request
 * depend of RequestURI.
 *
 * @author Ruslan Lazin
 */
class CommandResolver {
    private static final Logger logger = Logger.getLogger(CommandResolver.class);
    private static final Map<String, Command> mappingGET;
    private static final Map<String, Command> mappingPOST;
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    static {
        /*Initialize  application's GET paths
        * @see GETMapping.properties
        * @see PropertiesLoader
        */
        mappingGET = initializePaths(PropertiesLoader.getMappingGET());


        /*Initialize  application's POST paths
        * @see POSTMapping.properties
        * @see PropertiesLoader
        */
        mappingPOST = initializePaths(PropertiesLoader.getMappingPOST());
    }


    static Command getCommand(HttpServletRequest request) {
        switch (request.getMethod()) {
            case METHOD_GET:
                return mappingGET.get(request.getRequestURI());
            case METHOD_POST:
                return mappingPOST.get(request.getRequestURI());
            default:
                throw new UnsupportedOperationException(
                        "Method " + request.getMethod() + " isn't supported");
        }
    }


    private static Map<String, Command> initializePaths(Properties properties) {
        Map<String, Command> mapping = new HashMap<>();
        for (Object key : properties.keySet()) {
            String className = properties.getProperty((String) key);
            try {
                mapping.put((String) key, (Command) Class.forName(className).newInstance());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                logger.error("Cannot create instance of " + className, e);
            }
        }
        return mapping;
    }
}
