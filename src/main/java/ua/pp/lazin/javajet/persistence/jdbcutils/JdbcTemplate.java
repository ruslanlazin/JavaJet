package ua.pp.lazin.javajet.persistence.jdbcutils;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruslan Lazin
 */

public class JdbcTemplate<T> {
    private final static Logger logger = Logger.getLogger(JdbcTemplate.class);

    public Long insert(String insertQuery, Object... params) {
        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof java.util.Date) {
                    preparedStatement.setObject(i + 1, params[i], Types.TIMESTAMP);
                } else {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
            return null;
        } catch (SQLException e) {
            logger.error("Cannot execute insert query " + insertQuery, e);
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public int update(String updateQuery, Object... params) {
        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot execute update query " + updateQuery, e);
        } finally {
            closeConnection(connection);
        }
        return 0;
    }


    public List<T> findEntities(RowMapper<T> rowMapper, String query, Object... params) {
        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            ResultSet rs = stmt.executeQuery();

            List<T> entities = new ArrayList<>();
            int i = 0;
            while (rs.next()) {
                entities.add(rowMapper.mapRow(rs, i++));
            }
            return entities;
        } catch (SQLException e) {
            logger.error("Cannot execute query " + query, e);

        } finally {
            closeConnection(connection);
        }
        return null;

    }

    public T findEntity(RowMapper<T> rowMapper, String query, Object... params) {
        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rowMapper.mapRow(rs, 0);
            }
        } catch (SQLException e) {
            logger.error("Cannot execute query " + query, e);
        } finally {
            closeConnection(connection);
        }
        return null;
    }


    private void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Cannot close jdbc connection", e);
        }
    }

}