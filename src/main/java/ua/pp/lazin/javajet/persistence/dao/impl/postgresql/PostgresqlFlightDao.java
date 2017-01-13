package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.FlightDao;
import ua.pp.lazin.javajet.entity.Aircraft;
import ua.pp.lazin.javajet.entity.Airport;
import ua.pp.lazin.javajet.entity.Flight;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.persistence.jdbcutils.JdbcTemplate;
import ua.pp.lazin.javajet.persistence.jdbcutils.RowMapper;
import ua.pp.lazin.javajet.persistence.jdbcutils.TransactionCallback;
import ua.pp.lazin.javajet.persistence.jdbcutils.TransactionTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Postgresql flight dao.
 *
 * @author Ruslan Lazin
 */
public class PostgresqlFlightDao implements FlightDao {
    private static final JdbcTemplate<Flight> jdbcTemplate = new JdbcTemplate<>();
    private static final TransactionTemplate transactionTemplate = TransactionTemplate.getINSTANCE();
    private static final Integer START_VERSION = 0;
    private static final String CREATE =
            "INSERT INTO flight (" +
                    "departure_time, " +
                    "departure_timezone, " +
                    "aircraft_id, " +
                    "departure, " +
                    "destination, " +
                    "version) VALUES (?, ?, ?, ?, ?, ?);";

    private static final String FIND_BY_ID =
            "SELECT * FROM flight f " +
                    "JOIN aircraft a ON f.aircraft_id = a.aircraft_id " +
                    "WHERE f.flight_id = ?";

    private static final String FIND_ALL_ORDER_BY_DTIME_ASC =
            "SELECT * FROM flight f " +
                    "JOIN aircraft a ON f.aircraft_id = a.aircraft_id " +
                    "ORDER BY departure_time";

    private static final String FIND_ALL_BEFORE_THEN_ORDER_BY_DTIME_ASC =
            "SELECT * FROM flight f " +
                    "JOIN aircraft a ON f.aircraft_id = a.aircraft_id " +
                    "WHERE departure_time <= ? " +
                    "ORDER BY departure_time";

    private static final String FIND_ALL_LATER_THEN_ORDER_BY_DTIME_ASC =
            "SELECT * FROM flight f " +
                    "JOIN aircraft a ON f.aircraft_id = a.aircraft_id " +
                    "WHERE departure_time >= ? " +
                    "ORDER BY departure_time";

    private static final String UPDATE =
            "UPDATE flight SET " +
                    "departure_time = ?, " +
                    "departure = ?, " +
                    "destination = ?, " +
                    "aircraft_id = ?, " +
                    "departure_timezone = ?,   " +
                    "version = ? " +
                    "WHERE flight_id = ? " +
                    "AND version = ?";

    private static final String DELETE_LINKS =
            "DELETE FROM flight_users WHERE flight_id = ?";

    private static final String INSERT_LINK =
            "INSERT INTO flight_users (flight_id, user_id) VALUES (?, ?)";

    private static final RowMapper<Flight> rowMapper = new RowMapper<Flight>() {

        @Override
        public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {

            Aircraft aircraft = Aircraft.newBuilder()
                    .id(rs.getLong("aircraft_id"))
                    .model(rs.getString("model"))
                    .regNumber(rs.getString("reg_number")).build();
            Airport airportFrom = Airport.newBuilder().iataCode(rs.getString("departure")).build();
            Airport airportTo = Airport.newBuilder().iataCode(rs.getString("destination")).build();
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


    @Override
    public Long create(Flight flight) {
        return transactionTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(Connection connection) {
                return jdbcTemplate.insert(connection, CREATE,
                        new Timestamp(flight.getDepartureTime().getTime()),
                        flight.getDepartureTimezone(),
                        flight.getAircraft().getId(),
                        flight.getDeparture().getIataCode(),
                        flight.getDestination().getIataCode(),
                        START_VERSION);
            }
        });
    }

    @Override
    public Flight findById(Long flightId) {
        return jdbcTemplate.findEntity(rowMapper, FIND_BY_ID, flightId);
    }

    @Override
    public List<Flight> findAllOrderByDepartureTimeAsc() {
        return jdbcTemplate.findEntities(rowMapper, FIND_ALL_ORDER_BY_DTIME_ASC);
    }

    @Override
    public List<Flight> findAllBforeThen(Date date) {
        return jdbcTemplate.findEntities(rowMapper, FIND_ALL_BEFORE_THEN_ORDER_BY_DTIME_ASC,
                new Timestamp(date.getTime()));
    }

    @Override
    public List<Flight> findAllLaterThen(Date date) {
        return jdbcTemplate.findEntities(rowMapper, FIND_ALL_LATER_THEN_ORDER_BY_DTIME_ASC,
                new Timestamp(date.getTime()));
    }

    @Override
    public int update(Flight flight) {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(Connection connection) {
                return jdbcTemplate.update(connection, UPDATE,
                        new Timestamp(flight.getDepartureTime().getTime()),
                        flight.getDeparture().getIataCode(),
                        flight.getDestination().getIataCode(),
                        flight.getAircraft().getId(),
                        flight.getDepartureTimezone(),
                        flight.getVersion() + 1,
                        flight.getId(),
                        flight.getVersion());
            }
        });
    }


    @Override
    public int delete(Flight flight) {
        return 0;
    }

    @Override
    public Boolean updateWithCrew(Flight flight) {
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(Connection connection) {
                //check version and updateWithRoles flight if they match
                int numberOfUpdatedRows = jdbcTemplate.update(connection, UPDATE,
                        new Timestamp(flight.getDepartureTime().getTime()),
                        flight.getDeparture().getIataCode(),
                        flight.getDestination().getIataCode(),
                        flight.getAircraft().getId(),
                        flight.getDepartureTimezone(),
                        flight.getVersion() + 1,
                        flight.getId(),
                        flight.getVersion());
                if (numberOfUpdatedRows != 1) {
                    return false;
                }
                jdbcTemplate.update(connection, DELETE_LINKS, flight.getId());

                List<Object[]> rows = new ArrayList<>();
                if (flight.getCrew() != null) {
                    for (User user : flight.getCrew()) {
                        rows.add(new Object[]{flight.getId(), user.getId()});
                    }
                    jdbcTemplate.batchUpdate(connection, INSERT_LINK, rows);
                }
                return true;
            }
        });
    }
}