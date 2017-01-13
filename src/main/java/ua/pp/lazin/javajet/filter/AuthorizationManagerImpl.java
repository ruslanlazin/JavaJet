package ua.pp.lazin.javajet.filter;

import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.util.PropertiesLoader;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * @author Ruslan Lazin
 */
public class AuthorizationManagerImpl implements AuthorizationManager {

    //Contains role mappings.
    private static final Properties roleMappings = PropertiesLoader.getDBProperties();

    /**
     * Returns boolean indicating whether user has the appropriate role
     * for the specified URI.
     */
    @Override
    public boolean isUserAuthorized(User user, String uri) {

        boolean matchFound = false;
        boolean authorized = false;

        Iterator i = roleMappings.entrySet().iterator();

        //Loop through each URI mapping and check user's roles.
        //Exit once match is found.
        while ((!authorized) && (i.hasNext())) {
            Map.Entry me = (Map.Entry) i.next();

            //Pattern match.  '*' should be interpreted as a wildcard
            //for any ASCII character.
            String mapPattern =
                    ((String) me.getValue()).replaceAll("\\*", ".*");
            matchFound = Pattern.matches(mapPattern, uri);

            if (matchFound && user.getRoles().contains(me.getKey())) {
                authorized = true;
            }
        }
        return authorized;
    }
}

