package ua.pp.lazin.javajet.persistence.jdbcutils;

import java.sql.Connection;

/**
 * @author Ruslan Lazin
 */
public interface TransactionCallback<T> {
    T doInTransaction(Connection connection);
}
