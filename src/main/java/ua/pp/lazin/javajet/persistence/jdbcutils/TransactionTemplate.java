package ua.pp.lazin.javajet.persistence.jdbcutils;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.exception.DataAccessException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Ruslan Lazin
 */
public class TransactionTemplate {
    private static final Logger logger = Logger.getLogger(TransactionTemplate.class);
    private static TransactionTemplate INSTANCE = new TransactionTemplate();

    /**
     * Construct a new TransactionTemplate for usage.
     */
    private TransactionTemplate() {
    }

    public static TransactionTemplate getINSTANCE() {
        return INSTANCE;
    }


    public <T> T execute(TransactionCallback<T> action) {
        Connection connection = ConnectionManager.getConnection();

        T result = null;
        try {
            connection.setAutoCommit(false);
            result = action.doInTransaction(connection);
            connection.commit();
        } catch (SQLException e) {
            // Transactional code threw application exception -> rollback
            rollbackOnException(connection, e);
            throw new DataAccessException(e);
        } catch (DataAccessException e) {
            // Transactional code threw application exception -> rollback
            rollbackOnException(connection, e);
            throw e;
        } finally {
            closeConnection(connection);
        }
        return result;
    }


    /**
     * Perform a rollback, handling rollback exceptions properly.
     *
     * @param connection object representing the connection in setAutoCommit(false) state
     * @param ex         the thrown application exception or error
     * @throws DataAccessException in case of a rollback error
     */
    private void rollbackOnException(Connection connection, Throwable ex) {
        logger.debug("Initiating transaction rollback on application exception", ex);
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("Application exception overridden by rollback exception", e);
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

