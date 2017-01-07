package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.FlightDao;
import ua.pp.lazin.javajet.persistence.entity.Aircraft;
import ua.pp.lazin.javajet.persistence.entity.Airport;
import ua.pp.lazin.javajet.persistence.entity.Flight;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.persistence.jdbcutils.JdbcTemplate;
import ua.pp.lazin.javajet.persistence.jdbcutils.RowMapper;
import ua.pp.lazin.javajet.persistence.jdbcutils.TransactionCallback;
import ua.pp.lazin.javajet.persistence.jdbcutils.TransactionTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class PostgresqlFlightDao implements FlightDao {
    private static final JdbcTemplate<Flight> jdbcTemplate = new JdbcTemplate<>();
    private static final TransactionTemplate transactionTemplate = TransactionTemplate.getINSTANCE();
    private static final Integer START_VERSION = 0;
    private static final RowMapper<Flight> rowMapper = new RowMapper<Flight>() {

        @Override
        public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {

            Aircraft aircraft = new Aircraft();
            aircraft.setId(rs.getLong("aircraft_id"));
            aircraft.setModel(rs.getString("model"));
            aircraft.setRegNumber(rs.getString("reg_number"));
            Airport airportFrom = new Airport();
            airportFrom.setIataCode(rs.getString("departure"));
            Airport airportTo = new Airport();
            airportTo.setIataCode(rs.getString("destination"));

            return Flight.newBuilder()
                    .aircraft(aircraft)
                    .departure(airportFrom)
                    .destination(airportTo)
                    .departureTime(rs.getTimestamp("departure_time"))
                    .departureTimezone(rs.getString("departure_timezone"))
                    .id(rs.getLong("flight_id"))
                    .version(rs.getInt("version"))
                    .build();
        }
    };


    private static final String CREATE_SQL =
            "INSERT INTO flight (" +
                    "departure_time, " +
                    "departure_timezone, " +
                    "aircraft_id, " +
                    "departure, " +
                    "destination, " +
                    "version) VALUES (?, ?, ?, ?, ?, ?);";

    @Override
    public Long create(Flight flight) {
        return transactionTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(Connection connection) {
                return jdbcTemplate.insert(connection, CREATE_SQL,
                        new Timestamp(flight.getDepartureTime().getTime()),
                        flight.getDepartureTimezone(),
                        flight.getAircraft().getId(),
                        flight.getDeparture().getIataCode(),
                        flight.getDestination().getIataCode(),
                        START_VERSION);
            }
        });
    }


    private static final String FIND_ALL_SQL =
            "SELECT * FROM flight f " +
                    "JOIN aircraft a ON f.aircraft_id=a.aircraft_id " +
                    "ORDER BY departure_time";

    @Override
    public List<Flight> findAll() {
        return jdbcTemplate.findEntities(rowMapper, FIND_ALL_SQL);
    }

    @Override
    public List<Flight> findAllExpected() {
        return null;
    }


    private static final String UPDATE_SQL =
            "UPDATE flight SET " +
                    "departure_time = ?, " +
                    "departure = ?, " +
                    "destination = ?, " +
                    "aircraft_id = ?, " +
                    "departure_timezone = ?,   " +
                    "version = ? " +
                    "WHERE flight_id = ? " +
                    "AND version = ?";


    @Override
    public int update(Flight flight) {
        return jdbcTemplate.update(UPDATE_SQL,
                flight.getDepartureTime(),
                flight.getDeparture().getIataCode(),
                flight.getDestination().getIataCode(),
                flight.getAircraft().getId(),
                flight.getDepartureTimezone(),
                flight.getVersion() + 1,
                flight.getId(),
                flight.getVersion());
    }

    @Override
    public int delete(Flight flight) {
        return 0;
    }

    @Override
    public Flight findById(Long flightId) {
        return jdbcTemplate.findEntity(rowMapper, "SELECT * FROM flight f JOIN aircraft a ON " +
                "f.aircraft_id=a.aircraft_id WHERE f.flight_id = ?", flightId);
    }




    @Override
    public Boolean updateCrew(Flight flight) {
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(Connection connection) {
                jdbcTemplate.update("DELETE FROM flight_users WHERE flight_id = ?",
                        flight.getId());
                for (User user : flight.getCrew()) {
                    jdbcTemplate.insert(connection,
                            "INSERT INTO flight_users (flight_id, user_id) VALUES (?, ?)", flight.getId(), user.getId());
                }
                return true;
            }
        });
    }
}