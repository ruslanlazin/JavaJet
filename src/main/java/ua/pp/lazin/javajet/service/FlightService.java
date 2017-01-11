package ua.pp.lazin.javajet.service;

import com.google.maps.PendingResult;
import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.entity.Airport;
import ua.pp.lazin.javajet.persistence.dao.AirportDao;
import ua.pp.lazin.javajet.persistence.dao.FlightDao;
import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;
import ua.pp.lazin.javajet.util.AsynchronousTimezoneRetriever;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author Ruslan Lazin
 */
public class FlightService {
    private final static Logger logger = Logger.getLogger(FlightService.class);
    private final static FlightDao flightDao = DaoFactoryCreator.getFactory().getFlightDao();
    private final static UserDao userDao = DaoFactoryCreator.getFactory().getUserDao();
    private final static AirportDao airportDao = DaoFactoryCreator.getFactory().getAirportDao();
    private static FlightService INSTANCE = new FlightService();

    private FlightService() {
    }

    public static FlightService getINSTANCE() {
        return INSTANCE;
    }

    public List<Flight> findAll() {
        return flightDao.findAllOrderByDepartureTimeAsc();
    }

    public List<Flight> findAllLaterThen(Date date) {
        return flightDao.findAllLaterThen(date);
    }

    public Flight create(Flight flight) {
        Long id = flightDao.create(flight);
        flight.setId(id);
        resolveTimezoneWhenWrite(flight);
        return flight;
    }

    public Flight findById(Long flightId) {
        return flightDao.findById(flightId);
    }

    public Flight findByIdWithCrew(Long flightId) {
        Flight flight = flightDao.findById(flightId);
        Set<User> crew = userDao.findUsersByFlight(flight);
        flight.setCrew(crew);
        return flight;
    }

    public Flight findByIdWithCrewAirports(Long flightId) {
        Flight flight = flightDao.findById(flightId);
        flight.setCrew(userDao.findUsersByFlight(flight));
        flight.setDeparture(airportDao.findByCode(flight.getDeparture().getIataCode()));
        flight.setDestination(airportDao.findByCode(flight.getDestination().getIataCode()));
        return flight;
    }

    public boolean updateFlightAndCrew(Flight flight) {
        boolean result = flightDao.updateWithCrew(flight);
        if (flight.getDepartureTimezone() == null) {  // TODO: 08.01.2017 think
            resolveTimezoneWhenWrite(flight);
        }
        return result;
    }

    public boolean updateFlight(Flight flight) {
        int numberUpdatedRows = flightDao.update(flight);
        if (flight.getDepartureTimezone() == null) {  // TODO: 08.01.2017 think
            resolveTimezoneWhenWrite(flight);
        }
        return numberUpdatedRows == 1;

    }

    private void resolveTimezoneWhenWrite(Flight flight) {
        PendingResult.Callback<TimeZone> timeZoneCallback = new PendingResult.Callback<TimeZone>() {
            @Override
            public void onResult(TimeZone result) {
                logger.debug("TimeZone for Flight " + flight.getId() + ", Airport " +
                        flight.getDeparture().getName() + " resolved. It's " +
                        result.getID() + " Calling saving to db method");
                // TODO: 04.01.2017 tx start
                System.out.println(Thread.currentThread().getId());
                Flight freshFlight = flightDao.findById(flight.getId());
                freshFlight.setDepartureTimezone(result.getID());
                flightDao.update(freshFlight);
                //tx commit
            }

            @Override
            public void onFailure(Throwable e) {
                logger.error("Cannot resolve TimeZone for Flight " + flight, e);
            }
        };

        AsynchronousTimezoneRetriever resolver = new AsynchronousTimezoneRetriever();
        resolver.retrieveTimezoneWhenCall(flight.getDeparture(), timeZoneCallback);
    }
}
