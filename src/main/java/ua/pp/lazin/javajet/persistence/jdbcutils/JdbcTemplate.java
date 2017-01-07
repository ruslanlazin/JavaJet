package ua.pp.lazin.javajet.persistence.jdbcutils;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruslan Lazin
 */

public class JdbcTemplate<T> {
    private final static Logger logger = Logger.getLogger(JdbcTemplate.class);

    public Long insert(Connection connection, String insertQuery, Object... params) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Executing SQL INSERT [" + insertQuery + "]");
        }
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
            return null;
        } catch (SQLException e) {
            logger.error("Cannot execute INSERT query " + insertQuery, e);
            throw new DataAccessException(e);
        }
    }


    public int update(Connection connection, String updateQuery, Object... params) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Executing SQL UPDATE [" + updateQuery + "]");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot execute UPDATE query " + updateQuery, e);
            throw new DataAccessException(e);
        }
    }


    public List<T> findEntities(RowMapper<T> rowMapper, String query, Object... params) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Executing SQL SELECT [" + query + "]");
        }
        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            ResultSet rs = preparedStatement.executeQuery();

            List<T> entities = new ArrayList<>();
            int i = 0;
            while (rs.next()) {
                entities.add(rowMapper.mapRow(rs, i++));
            }
            return entities;
        } catch (SQLException e) {
            logger.error("Cannot execute SELECT query " + query, e);
            throw new DataAccessException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public T findEntity(RowMapper<T> rowMapper, String query, Object... params) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Executing SQL SELECT [" + query + "]");
        }
        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rowMapper.mapRow(rs, 0);
            }
            return null;
        } catch (SQLException e) {
            logger.error("Cannot execute SELECT query " + query, e);
            throw new DataAccessException(e);
        } finally {
            closeConnection(connection);
        }
    }


    public int[] batchUpdate(Connection txConnection, String batchQuery, List<Object[]> batchArgs) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Executing SQL BATCH UPDATE [" + batchQuery + "] with a batch size of " + batchArgs.size());
        }
        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(batchQuery)) {

            for (Object[] rowParams : batchArgs) {
                for (int i = 0; i < rowParams.length; i++) {
                    stmt.setObject(i + 1, rowParams[i]);
                }
                stmt.addBatch();
            }
            return stmt.executeBatch();

        } catch (SQLException e) {
            logger.error("Cannot execute BATCH UPDATE query " + batchQuery, e);
            throw new DataAccessException(e);
        }
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