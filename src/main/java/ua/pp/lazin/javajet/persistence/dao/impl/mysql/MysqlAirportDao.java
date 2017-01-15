package ua.pp.lazin.javajet.persistence.dao.impl.mysql;

import ua.pp.lazin.javajet.entity.Airport;
import ua.pp.lazin.javajet.persistence.dao.AirportDao;
import ua.pp.lazin.javajet.persistence.jdbcutils.JdbcTemplate;
import ua.pp.lazin.javajet.persistence.jdbcutils.RowMapper;
import ua.pp.lazin.javajet.persistence.jdbcutils.TransactionCallback;
import ua.pp.lazin.javajet.persistence.jdbcutils.TransactionTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class MysqlAirportDao implements AirportDao {
    private static final JdbcTemplate<Airport> jdbcTemplate = new JdbcTemplate<>();
    private static final TransactionTemplate transactionTemplate = TransactionTemplate.getINSTANCE();

    private static final RowMapper<Airport> rowMapper = new RowMapper<Airport>() {
        @Override
        public Airport mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Airport.newBuilder()
                    .iataCode(rs.getString("iata_code"))
                    .name(rs.getString("name_eng"))
                    .city(rs.getString("city_eng"))
                    .country(rs.getString("country_eng"))
                    .latitude(rs.getDouble("latitude"))
                    .longitude(rs.getDouble("longitude"))
                    .build();
        }
    };

    private static final String CREATE =
            "INSERT INTO airport (" +
                    "iata_code, " +
                    "name_eng, " +
                    "city_eng, " +
                    "country_eng, " +
                    "latitude, " +
                    "longitude) VALUES(?, ?, ?, ?, ?, ?);";

    private static final String UPDATE =
            "UPDATE airport SET " +
                    "name_eng = ? " +
                    "city_eng = ? " +
                    "country_eng = ? " +
                    "latitude = ? " +
                    "longitude = ? " +
                    "WHERE iata_code= ?";

    private static final String DELETE_LINKS =
            "DELETE FROM flight f WHERE f.departure = ? OR f.destination = ?";

    private static final String DELETE =
            "DELETE FROM airport a WHERE a.iata_code = ?";

    private static final String FIND_BY_CODE =
            "SELECT * FROM airport a WHERE a.iata_code = ?";

    private static final String FIND_ALL =
            "SELECT * FROM airport";

    private static AirportDao INSTANCE = new MysqlAirportDao();

    private MysqlAirportDao() {
    }

    public static AirportDao getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Long create(Airport airport) {
        return transactionTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(Connection connection) {
                return jdbcTemplate.insert(connection, CREATE,
                        airport.getName(),
                        airport.getCity(),
                        airport.getCountry(),
                        airport.getLatitude(),
                        airport.getLongitude(),
                        airport.getIataCode());
            }
        });
    }

    @Override
    public int update(Airport airport) {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(Connection connection) {
                return jdbcTemplate.update(connection, UPDATE,
                        airport.getName(),
                        airport.getCity(),
                        airport.getCountry(),
                        airport.getLatitude(),
                        airport.getLongitude(),
                        airport.getIataCode());
            }
        });
    }

    @Override
    public int delete(Airport airport) {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(Connection connection) {
                return jdbcTemplate.update(connection, DELETE,
                        airport.getIataCode());
            }
        });
    }

    @Override
    public int deleteCascade(Airport airport) {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(Connection connection) {

                jdbcTemplate.update(connection, DELETE_LINKS,
                        airport.getIataCode(),
                        airport.getIataCode());

                return jdbcTemplate.update(connection, DELETE,
                        airport.getIataCode());
            }
        });
    }

    @Override
    public Airport findByCode(String iataCode) {
        return jdbcTemplate.findEntity(rowMapper, FIND_BY_CODE, iataCode);
    }

    @Override
    public List<Airport> findAll() {
        return jdbcTemplate.findEntities(rowMapper, FIND_ALL);
    }
}
