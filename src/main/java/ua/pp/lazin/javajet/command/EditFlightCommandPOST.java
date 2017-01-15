package ua.pp.lazin.javajet.command;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.entity.Aircraft;
import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.service.AircraftService;
import ua.pp.lazin.javajet.service.AirportService;
import ua.pp.lazin.javajet.service.FlightService;
import ua.pp.lazin.javajet.util.DateParser;
import ua.pp.lazin.javajet.util.EntityParser;

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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Flight flight = EntityParser.parseFlightWithAirports(request);
        flight.setDeparture(airportService.findByCode(flight.getDeparture().getIataCode()));
        flight.setDestination(airportService.findByCode(flight.getDestination().getIataCode()));

        // TODO: 29.12.2016 validate

        // IF Flight was just entered
        if (flight.getId() == null) {
            flight = flightService.create(flight);
            request.setAttribute(KEY_SUCCESS, true);

        } else {
            // IF we are editing existing Flight
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
