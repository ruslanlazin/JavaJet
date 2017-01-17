package ua.pp.lazin.javajet.validation;

import ua.pp.lazin.javajet.entity.Flight;

/**
 * The Airports present validator check if flight's airports present in our db
 *
 * @author Ruslan Lazin
 */
class AirportsPresentValidator extends AbstractValidator<Flight> {

    @Override
    public Errors validate(Flight flight, Errors errors) {
        boolean criticalError = false;
        if (flight.getDeparture() == null) {
            criticalError = true;
            errors.addError(new ObjectError("Departure Airport not present in db",
                    "errors.departure.not.found"));
        }
        if (flight.getDestination() == null) {
            criticalError = true;
            errors.addError(new ObjectError("Destination Airport not present in db",
                    "errors.destination.not.found"));
        }
        if (criticalError){
            return errors;
        }
        return doNext(flight, errors);
    }
}
