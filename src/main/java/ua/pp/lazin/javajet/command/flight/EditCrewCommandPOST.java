package ua.pp.lazin.javajet.command.flight;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.command.Command;
import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.service.FlightService;
import ua.pp.lazin.javajet.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ruslan Lazin
 */
public class EditCrewCommandPOST implements Command {
    private static final Logger logger = Logger.getLogger(EditCrewCommandPOST.class);
    private static final FlightService flightService = FlightService.getINSTANCE();
    private static final UserService userService = UserService.getINSTANCE();

    private static final String FLIGHT_ATTRIBUTE = "flight";
    private static final String EMPLOYEES_ATTRIBUTE = "employees";
    private static final String SUCCESS_ATTRIBUTE = "success";
    private static final String CONCURRENT_MODIFICATION_ATTRIBUTE = "concurrent";
    private static final String FLIGHT_ID_PARAMETER = "flightId";
    private static final String VERSION_PARAMETER = "version";
    private static final String CREW_PARAMETER = "crew";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Set<User> crew = new HashSet<>();
        String[] crewIdsAsStrings = request.getParameterValues(CREW_PARAMETER);
        if (crewIdsAsStrings != null) {
            for (String userIdAsString : crewIdsAsStrings) {
                crew.add(User.newBuilder().id(Long.valueOf(userIdAsString)).build());
            }
        }
        Flight flight = flightService.findById(Long.valueOf(request.getParameter(FLIGHT_ID_PARAMETER)));
        flight.setCrew(crew);
        flight.setVersion(Integer.valueOf(request.getParameter(VERSION_PARAMETER)));

        Boolean isUpdateSuccessful = flightService.updateFlightAndCrew(flight);
        if (isUpdateSuccessful) {
            request.setAttribute(SUCCESS_ATTRIBUTE, true);
        } else {
            request.setAttribute(CONCURRENT_MODIFICATION_ATTRIBUTE, true);
            logger.info("Two or more users tried to edit Flight " + flight.getId() + " simultaneously");
        }
        request.setAttribute(FLIGHT_ATTRIBUTE, flightService.findByIdWithCrewAirports(flight.getId()));
        request.setAttribute(EMPLOYEES_ATTRIBUTE, userService.findAllWorkingAirCrewMembers());

        return "edit-crew";
    }
}
