package ua.pp.lazin.javajet.persistence.dao.impl.postgresql;

import ua.pp.lazin.javajet.persistence.dao.PositionDao;
import ua.pp.lazin.javajet.persistence.entity.Position;
import ua.pp.lazin.javajet.persistence.jdbcutils.JdbcTemplate;
import ua.pp.lazin.javajet.persistence.jdbcutils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class PostgresqlPositionDao implements PositionDao {
    private static final JdbcTemplate<Position> jdbcTemplate = new JdbcTemplate<>();
    private static final RowMapper<Position> rowMapper = new RowMapper<Position>() {
        @Override
        public Position mapRow(ResultSet rs, int rowNum) throws SQLException {
            Position position = new Position();
            position.setId(rs.getLong("position_id"));
            position.setTitle(rs.getString("title"));
            return position;
        }
    };

    @Override
    public Long create(Position position) {
        return null;
    }

    @Override
    public Position findByTitle(String title) {
        return jdbcTemplate.findEntity(rowMapper, "SELECT * FROM position p WHERE p.title = ?", title);
    }

    @Override
    public List<Position> findAll() {
        return jdbcTemplate.findEntities(rowMapper, "SELECT * FROM position ORDER BY title");
    }

    @Override
    public int update(Position position) {
        return 0;
    }

    @Override
    public int delete(Position position) {
        return 0;
    }
}
