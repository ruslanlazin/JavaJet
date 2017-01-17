package ua.pp.lazin.javajet.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * The basic implementation of Error interface.
 *
 * @author Ruslan Lazin
 */
public class FlightErrors implements Errors {

    private List<ObjectError> allErrors = new ArrayList<>();

    @Override
    public void addError(ObjectError error) {
        allErrors.add(error);
    }

    @Override
    public boolean hasErrors() {
        return !allErrors.isEmpty();
    }

    @Override
    public int getErrorCount() {
        return allErrors.size();
    }

    @Override
    public List<ObjectError> getAllErrors() {
        return allErrors;
    }
}
