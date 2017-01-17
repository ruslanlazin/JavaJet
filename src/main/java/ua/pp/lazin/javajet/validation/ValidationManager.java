package ua.pp.lazin.javajet.validation;

import ua.pp.lazin.javajet.entity.Flight;

/**
 * The type Validation manager.
 *
 * @author Ruslan Lazin
 */
public class ValidationManager {

    private static final ValidationManager INSTANCE = new ValidationManager();

    private ValidationManager() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ValidationManager getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Validate for errors.
     * Implements Chain of responsibility pattern
     *
     * @param flight the flight
     * @return the errors
     */
    public Errors validate(Flight flight) {

        Errors errors = new FlightErrors();
        Validator<Flight> validator = new AirportsPresentValidator()
                .setNextValidator(new AirportsAcceptValidator());

        return validator.validate(flight, errors);
    }
}
