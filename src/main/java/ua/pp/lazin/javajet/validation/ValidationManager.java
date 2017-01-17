package ua.pp.lazin.javajet.validation;

import ua.pp.lazin.javajet.entity.Flight;

/**
 * @author Ruslan Lazin
 */
public class ValidationManager {

    public Errors validate(Flight flight) {
        Errors errors = new FlightErrors();

        Validator<Flight> validator = new AirportsPresentValidator()
                .setNextValidator(new AirportsAcceptValidator());

        return validator.validate(flight, errors);
    }
}
