package ua.pp.lazin.javajet.command;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.entity.Aircraft;
import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.service.AircraftService;
import ua.pp.lazin.javajet.service.AirportService;
import ua.pp.lazin.javajet.service.FlightService;
import ua.pp.lazin.javajet.util.DateParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class EditFlightCommandPOST implements Command {
    private static final Logger logger = Logger.getLogger(EditFlightCommandPOST.class);
    private static final FlightService flightService = FlightService.getINSTANCE();
    private static final AircraftService aircraftService = AircraftService.getINSTANCE();
    private static final AirportService airportService = AirportService.getINSTANCE();
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_CONCURRENT_MODIFICATION = "concurrent";
    private static final String AIRCRAFTS_ATTRIBUTE = "aircrafts";
    private static final String FLIGHT_ATTRIBUTE = "flight";
    private static final String FLIGHT_ID_PARAMETER = "flightId";
    private static final String AIRCRAFT_PARAMETER = "aircraft";
    private static final String FROM_PARAMETER = "from";
    private static final String TO_PARAMETER = "to";
    private static final String DEPARTURE_TIME_PARAMETER = "departureTime";
    private static final String VERSION_PARAMETER = "version";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Aircraft aircraft = Aircraft.newBuilder()
                .id(Long.valueOf(request.getParameter(AIRCRAFT_PARAMETER))).build();

        Flight flight = Flight.newBuilder()
                .departureTime(new DateParser().parseUTC(request.getParameter(DEPARTURE_TIME_PARAMETER)))
                .departure(airportService.findByCode(request.getParameter(FROM_PARAMETER)))
                .destination(airportService.findByCode(request.getParameter(TO_PARAMETER)))
                .aircraft(aircraft)
                .build();

        // TODO: 29.12.2016 validate
        String flightIdAsString = request.getParameter(FLIGHT_ID_PARAMETER);
        if (flightIdAsString == null || flightIdAsString.isEmpty()) {
            flight = flightService.create(flight);
            request.setAttribute(KEY_SUCCESS, true);
        } else {
            Long flightId = Long.valueOf(flightIdAsString);
            flight.setId(flightId);
            flight.setVersion(Integer.valueOf(request.getParameter(VERSION_PARAMETER)));

            Boolean isUpdateSuccessful = flightService.updateFlight(flight);
            if (isUpdateSuccessful) {
                request.setAttribute(KEY_SUCCESS, true);
            } else {
                request.setAttribute(KEY_CONCURRENT_MODIFICATION, true);
                logger.info("Two or more users tried to edit Flight " + flight.getId() + " simultaneously");
            }
        }

        request.setAttribute(FLIGHT_ATTRIBUTE, flightService.findByIdWithCrew(flight.getId()));
        request.setAttribute(AIRCRAFTS_ATTRIBUTE, aircraftService.findAll());

        return "edit-flight";
    }
}
