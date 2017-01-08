package ua.pp.lazin.javajet.command;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.entity.Aircraft;
import ua.pp.lazin.javajet.persistence.entity.Flight;
import ua.pp.lazin.javajet.service.AircraftService;
import ua.pp.lazin.javajet.service.AirportService;
import ua.pp.lazin.javajet.service.FlightService;
import ua.pp.lazin.javajet.util.DateParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Ruslan Lazin
 */
public class AddFlightCommandPOST implements Command {
    private static final Logger logger = Logger.getLogger(AddFlightCommandPOST.class);
    private static final FlightService flightService = FlightService.getINSTANCE();
    private static final AircraftService aircraftService = AircraftService.getINSTANCE();
    private static final AirportService airportService = AirportService.getINSTANCE();

    private static final String AIRCRAFTS_ATTRIBUTE = "aircrafts";
    private static final String SUCCESS_ATTRIBUTE = "success";
    private static final String AIRCRAFT_PARAMETER = "aircraft";
    private static final String FROM_PARAMETER = "from";
    private static final String TO_PARAMETER = "to";
    private static final String DEPARTURE_TIME_PARAMETER = "departureTime";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Aircraft aircraft = Aircraft.newBuilder()
                .id(Long.valueOf(request.getParameter(AIRCRAFT_PARAMETER))).build();

        Flight flight = Flight.newBuilder()
                .departure(airportService.findByCode(request.getParameter(FROM_PARAMETER)))
                .departureTime(new DateParser().parseUTC(request.getParameter(DEPARTURE_TIME_PARAMETER)))
                .destination(airportService.findByCode(request.getParameter(TO_PARAMETER)))
                .aircraft(aircraft)
                .build();

        flightService.create(flight); // TODO: 29.12.2016 validate

        request.setAttribute(AIRCRAFTS_ATTRIBUTE, aircraftService.findAll());
        request.setAttribute(SUCCESS_ATTRIBUTE, true);

        return "add-flight";
    }
}
