package ua.pp.lazin.javajet.persistence.jdbcutils;

import java.sql.Connection;

/**
 * The interface Transaction callback.
 *
 * @param <T> the type parameter
 * @author Ruslan Lazin
 */
public interface TransactionCallback<T> {
    /**
     * Do in transaction. Implementations must implement this method to
     * do all the job, when transaction will be started and committed dy TransactionTemplate
     *
     * @param connection the connection
     * @return the t
     */
    T doInTransaction(Connection connection);
}
