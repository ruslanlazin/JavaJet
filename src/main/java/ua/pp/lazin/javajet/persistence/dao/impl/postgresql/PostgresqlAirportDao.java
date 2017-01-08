package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.AirportDao;
import ua.pp.lazin.javajet.persistence.entity.Airport;
import ua.pp.lazin.javajet.persistence.jdbcutils.JdbcTemplate;
import ua.pp.lazin.javajet.persistence.jdbcutils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class PostgresqlAirportDao implements AirportDao {
    private static final JdbcTemplate<Airport> jdbcTemplate = new JdbcTemplate<>();
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

    @Override
    public Long create(Airport airport) {
        return null;
    }

    @Override
    public Airport findByCode(String iataCode) {
        return jdbcTemplate.findEntity(rowMapper,
                "SELECT * FROM airport a WHERE a.iata_code = ?", iataCode);
    }

    @Override
    public List<Airport> findAll() {
        return jdbcTemplate.findEntities(rowMapper, "SELECT * FROM airport");
    }

    @Override
    public int update(Airport airport) {
        return 0;
    }

    @Override
    public int delete(Airport airport) {
        return 0;
    }
}
