package ua.pp.lazin.javajet.util;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.entity.*;
import ua.pp.lazin.javajet.service.AirportService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ruslan Lazin
 */
public class EntityParser {

    private static final Logger logger = Logger.getLogger(EntityParser.class);

    private static final String USER_ID_PARAMETER = "userId";
    private static final String USERNAME_PARAMETER = "username";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String FIRST_NAME_PARAMETER = "firstname";
    private static final String SECOND_NEME_PARAMETER = "secondname";
    private static final String EMAIL_PARAMETER = "email";
    private static final String POSITION_PARAMETER = "position";
    private static final String WORKING_PARAMETER = "working";
    private static final String VERSION_PARAMETER = "version";
    private static final String USER_ROLES_PARAMETER = "userRoles";

    private static final String FLIGHT_ID_PARAMETER = "flightId";
    private static final String AIRCRAFT_PARAMETER = "aircraft";
    private static final String FROM_PARAMETER = "from";
    private static final String TO_PARAMETER = "to";
    private static final String DEPARTURE_TIME_PARAMETER = "departureTime";
    private static final String TIMEZONE_PARAMETER = "timeZone";


    private EntityParser() {
    }

    /**
     * @param request to parse
     * @return User entity. All fields absent in the request will be null
     */
    public static User parseUserWithRoles(HttpServletRequest request) {

        User user = User.newBuilder()
                .id(parseLong(request.getParameter(USER_ID_PARAMETER)))
                .username(request.getParameter(USERNAME_PARAMETER))
                .password(request.getParameter(PASSWORD_PARAMETER))
                .firstName(request.getParameter(FIRST_NAME_PARAMETER))
                .secondName(request.getParameter(SECOND_NEME_PARAMETER))
                .email(request.getParameter(EMAIL_PARAMETER))
                .position(Position.newBuilder().id(parseLong(request.getParameter(POSITION_PARAMETER))).build())
                .working(Boolean.valueOf(request.getParameter(WORKING_PARAMETER)))
                .version(parseInteger(request.getParameter(VERSION_PARAMETER)))
                .build();

        String[] rolesIdAsStrings = request.getParameterValues(USER_ROLES_PARAMETER);
        if (rolesIdAsStrings != null) {
            Set<Role> userRoles = new HashSet<>(rolesIdAsStrings.length);
            for (String roleIdAsString : rolesIdAsStrings) {
                userRoles.add(Role.newBuilder().id(Long.valueOf(roleIdAsString)).build());
            }
            user.setRoles(userRoles);
        }

        return user;
    }

    /**
     * @param request to parse
     * @return Flight entity. All fields absent in the request will be null
     */
    public static Flight parseFlightWithAirports(HttpServletRequest request) {

        return Flight.newBuilder()
                .id(parseLong(request.getParameter(FLIGHT_ID_PARAMETER)))
                .departureTime(new DateParser().parseUTC(request.getParameter(DEPARTURE_TIME_PARAMETER)))
                .departure(Airport.newBuilder().iataCode(request.getParameter(FROM_PARAMETER)).build())
                .destination(Airport.newBuilder().iataCode(request.getParameter(TO_PARAMETER)).build())
                .departureTimezone(request.getParameter(TIMEZONE_PARAMETER))
                .aircraft(Aircraft.newBuilder().id(parseLong(request.getParameter(AIRCRAFT_PARAMETER))).build())
                .version(parseInteger(request.getParameter(VERSION_PARAMETER)))
                .build();
    }


    /**
     * @param string to parse
     * @return Long value of param string.
     * Returns null if it's imposable to parse that string
     */
    private static Long parseLong(String string) {
        if (string != null) {
            try {
                return Long.valueOf(string);
            } catch (NumberFormatException e) {
                logger.error("Can't parse " + string + "as Long", e);
            }
        }
        return null;
    }


    /**
     * @param string to parse
     * @return Integer value of param string.
     * Returns null if it's imposable to parse that string
     */
    private static Integer parseInteger(String string) {
        if (string != null) {
            try {
                return Integer.valueOf(string);
            } catch (NumberFormatException e) {
                logger.error("Can't parse " + string + "as Integer", e);
            }
        }
        return null;
    }
}