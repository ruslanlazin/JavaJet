package ua.pp.lazin.javajet.command.aircraft;

import ua.pp.lazin.javajet.command.Command;
import ua.pp.lazin.javajet.entity.Aircraft;
import ua.pp.lazin.javajet.service.AircraftService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruslan Lazin
 */
public class AircraftsCommandPOST implements Command {
    private AircraftService aircraftService = AircraftService.getINSTANCE();
    private static final String AIRCRAFTS_ATTRIBUTE = "aircrafts";
    private static final String AIRCRAFT_ID_PARAMETER = "aircraftId";
    private static final String REG_NUMBER_PARAMETER = "regNumber";
    private static final String MODEL_PARAMETER = "model";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        Aircraft aircraft = Aircraft.newBuilder()
                .regNumber(request.getParameter(REG_NUMBER_PARAMETER))
                .model(request.getParameter(MODEL_PARAMETER))
                .build();

        String aircraftIdAsString = request.getParameter(AIRCRAFT_ID_PARAMETER);
        if (aircraftIdAsString == null || aircraftIdAsString.isEmpty()) {
            aircraftService.create(aircraft);
        } else {
            Long aircraftId = Long.valueOf(aircraftIdAsString);
            aircraft.setId(aircraftId);
            aircraftService.update(aircraft);
        }

        request.setAttribute(AIRCRAFTS_ATTRIBUTE, aircraftService.findAll());
        return "aircrafts";
    }
}

