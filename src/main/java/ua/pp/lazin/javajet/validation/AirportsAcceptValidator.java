package ua.pp.lazin.javajet.validation;

import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.util.PropertiesLoader;

import java.util.Properties;

/**
 * The validator checks if flights to/from Airports wasn't prohibited
 * by any controlling organisation declared in  prohibitedAirports.properties
 *
 * @author Ruslan Lazin
 */
public class AirportsAcceptValidator extends AbstractValidator<Flight> {
    private static final Properties prohibitedAirportCodes =
            PropertiesLoader.loadPropertiesFromFile("prohibitedAirports.properties");

    @Override
    public Errors validate(Flight flight, Errors errors) {

        if (prohibitedAirportCodes.containsKey(flight.getDeparture().getIataCode())) {
            errors.addError(new ObjectError("Flying to Airport " +
                    flight.getDeparture().getName() + " was prohibited by " +
                    prohibitedAirportCodes.getProperty(flight.getDeparture().getIataCode()),
                    "errors.airport.not.acceptable"));
        }
        if (prohibitedAirportCodes.containsKey(flight.getDestination().getIataCode())) {
            errors.addError(new ObjectError("Flying to Airport " +
                    flight.getDestination().getName() + " was prohibited by " +
                    prohibitedAirportCodes.getProperty(flight.getDestination().getIataCode()),
                    "errors.airport.not.acceptable"));
        }
        return doNext(flight, errors);
    }
}
