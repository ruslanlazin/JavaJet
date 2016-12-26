package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.FlightDao;
import ua.pp.lazin.javajet.persistence.entity.Aircraft;
import ua.pp.lazin.javajet.persistence.entity.Airport;
import ua.pp.lazin.javajet.persistence.entity.Flight;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.persistence.jdbcutils.JdbcTemplate;
import ua.pp.lazin.javajet.persistence.jdbcutils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class PostgresqlFlightDao implements FlightDao {
    private static final JdbcTemplate<Flight> jdbcTemplate = new JdbcTemplate<>();
    private static final RowMapper<Flight> rowMapper = new RowMapper<Flight>() {

        @Override
        public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
            Flight flight = new Flight();
            flight.setId(rs.getLong("flight_id"));
            flight.setDepartureTime(rs.getTimestamp("departure_time"));

            Aircraft aircraft = new Aircraft();
            aircraft.setId(rs.getLong("aircraft_id"));
            aircraft.setModel(rs.getString("model"));
            aircraft.setRegNumber(rs.getString("reg_number"));
            flight.setAircraft(aircraft);

            Airport airportTo = new Airport();
            airportTo.setIataCode(rs.getString("to"));
            flight.setTo(airportTo);

            Airport airportFrom = new Airport();
            airportFrom.setIataCode(rs.getString("from"));
            flight.setFrom(airportFrom);

            return flight;
        }
    };


    @Override
    public Long create(Flight flight) {
        return null;
    }

    @Override
    public User findByID(Long id) {
        return null;
    }

    @Override
    public List<Flight> findAll() {
        return jdbcTemplate.findEntities(rowMapper,
                "SELECT * FROM flight f JOIN aircraft a ON " +
                "f.aircraft_id=a.aircraft_id");
    }

    @Override
    public List<Flight> findAllExpected() {
        return null;
    }

    @Override
    public int update(Flight flight) {
        return 0;
    }

    @Override
    public int delete(Flight flight) {
        return 0;
    }
}
