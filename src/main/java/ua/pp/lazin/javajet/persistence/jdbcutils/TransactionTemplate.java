package ua.pp.lazin.javajet.persistence.jdbcutils;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.exception.DataAccessException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The Transaction template encapsulates all repeated code
 * for starting and committing transactions
 *
 * @author Ruslan Lazin
 */
public class TransactionTemplate {
    private static final Logger logger = Logger.getLogger(TransactionTemplate.class);
    private static final int TX_ISOLATION_LEVEL = Connection.TRANSACTION_READ_COMMITTED;
    private static final TransactionTemplate INSTANCE = new TransactionTemplate();

    /**
     * Construct a new TransactionTemplate for usage.
     */
    private TransactionTemplate() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static TransactionTemplate getINSTANCE() {
        return INSTANCE;
    }


    /**
     * Execute
     *
     * @param <T>    the type parameter
     * @param action the action
     * @return the t
     */
    public <T> T execute(TransactionCallback<T> action) {
        Connection txConnection = ConnectionManager.getConnection();

        T result = null;
        try {
            txConnection.setAutoCommit(false);
            txConnection.setTransactionIsolation(TX_ISOLATION_LEVEL);
            result = action.doInTransaction(txConnection);
            txConnection.commit();
            txConnection.setAutoCommit(true);
        } catch (SQLException e) {
            // Transactional code threw application exception -> rollback
            rollbackOnException(txConnection, e);
            throw new DataAccessException(e);
        } catch (DataAccessException e) {
            // Transactional code threw application exception -> rollback
            rollbackOnException(txConnection, e);
            throw e;
        } finally {
            closeConnection(txConnection);
        }
        return result;
    }


    /**
     * Perform a rollback, handling rollback exceptions properly.
     *
     * @param txConnection object representing the connection in setAutoCommit(false) state
     * @param ex           the thrown application exception or error
     * @throws DataAccessException in case of a rollback error
     */
    private void rollbackOnException(Connection txConnection, Throwable ex) {
        logger.debug("Initiating transaction rollback on application exception", ex);
        try {
            txConnection.rollback();
        } catch (SQLException e) {
            logger.error("Application exception overridden by rollback exception", e);
            throw new DataAccessException(e);
        }
    }

    private void closeConnection(Connection txConnection) {
        try {
            if (txConnection != null) {
                txConnection.close();
            }
        } catch (SQLException e) {
            logger.error("Cannot close jdbc connection", e);
        }
    }

}

