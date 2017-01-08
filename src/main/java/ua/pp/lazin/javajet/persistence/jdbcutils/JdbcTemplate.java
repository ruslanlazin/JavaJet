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
 * The Jdbc template.
 *
 * @param <T> the type parameter
 * @author Ruslan Lazin
 */
public class JdbcTemplate<T> {
    private final static Logger logger = Logger.getLogger(JdbcTemplate.class);

    /**
     * Insert row in the table
     *
     * @param txConnection the connection. You have to open and close it outside of this method.
     * @param insertQuery  the insert query
     * @param params       the params to set in PreparedStatement
     * @return the long representing Generated key value
     * @throws DataAccessException the data access exception
     */
    public Long insert(Connection txConnection, String insertQuery, Object... params) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Executing SQL INSERT [" + insertQuery + "]");
        }
        try (PreparedStatement preparedStatement = txConnection
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

    /**
     * Update/delete row in the table
     *
     * @param txConnection the connection. You have to open and close it outside of this method.
     * @param updateQuery  the update query
     * @param params        the params to set in PreparedStatement
     * @return the int - number of updated rows
     * @throws DataAccessException the data access exception
     */
    public int update(Connection txConnection, String updateQuery, Object... params) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Executing SQL UPDATE [" + updateQuery + "]");
        }
        try (PreparedStatement preparedStatement = txConnection.prepareStatement(updateQuery)) {

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot execute UPDATE query " + updateQuery, e);
            throw new DataAccessException(e);
        }
    }


    /**
     * Batch update
     *
     * @param txConnection the connection. You have to open and close it outside of this method.
     * @param batchQuery   the batch query
     * @param batchArgs    the batch args  the params to set in PreparedStatement
     * @return the int [ ] - number of updated rows for each sql query
     * @throws DataAccessException the data access exception
     */
    public int[] batchUpdate(Connection txConnection, String batchQuery, List<Object[]> batchArgs) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Executing SQL BATCH UPDATE [" + batchQuery + "] with a batch size of " + batchArgs.size());
        }
        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(batchQuery)) {

            if (connection.getMetaData().supportsBatchUpdates()) {
                for (Object[] rowParams : batchArgs) {
                    for (int i = 0; i < rowParams.length; i++) {
                        stmt.setObject(i + 1, rowParams[i]);
                    }
                    stmt.addBatch();
                }
                return stmt.executeBatch();

            } else {
                logger.warn("JDBC Driver does not support Batch updates; resorting to single statement execution");
                int[] result = new int[batchArgs.size()];
                int rowNumber = 0;
                for (Object[] rowParams : batchArgs) {
                    for (int i = 0; i < rowParams.length; i++) {
                        stmt.setObject(i + 1, rowParams[i]);
                    }
                    result[rowNumber] = stmt.executeUpdate();
                    rowNumber++;
                }
                return result;
            }
        } catch (SQLException e) {
            logger.error("Cannot execute BATCH UPDATE query " + batchQuery, e);
            throw new DataAccessException(e);
        }
    }

    /**
     * Find entities <T>
     *
     * @param rowMapper the row mapper to parse the ResultSet
     * @param query     the query
     * @param params     the params to set in PreparedStatement
     * @return the list of <T> Entities
     * @throws DataAccessException the data access exception
     */
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
            int rowNumber = 0;
            while (rs.next()) {
                entities.add(rowMapper.mapRow(rs, rowNumber++));
            }
            return entities;
        } catch (SQLException e) {
            logger.error("Cannot execute SELECT query " + query, e);
            throw new DataAccessException(e);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Find entity <T>
     *
     * @param rowMapper the row mapper to parse the ResultSet
     * @param query     the query
     * @param params     the params to set in PreparedStatement
     * @return the <T> Entity
     * @throws DataAccessException the data access exception
     */
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