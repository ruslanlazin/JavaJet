package ua.pp.lazin.javajet.persistence;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.exception.DataAccessException;
import ua.pp.lazin.javajet.util.PropertiesLoader;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Ruslan Lazin
 */
public class ConnectionManager {
    private static DataSource dataSource;
    private static final Logger logger = Logger.getLogger(ConnectionManager.class);


    static {
        Properties properties = PropertiesLoader.getDBProperties();
        String dbName = properties.getProperty("db.name").toLowerCase();

        try {
            switch (dbName) {
                case "mysql": {
                    logger.debug("Creating JNDI DataSource to MySql DB");
                    Context envContext = (Context) new InitialContext().lookup("java:comp/env");
                    dataSource = (DataSource) envContext.lookup(properties
                            .getProperty("mysql.datasource.name"));
                    break;
                }
                case "postgresql": {
                    logger.debug("Creating JNDI DataSource to PostgreSQL DB");
                    Context envContext = (Context) new InitialContext().lookup("java:comp/env");
                    dataSource = (DataSource) envContext.lookup(properties
                            .getProperty("postgresql.datasource.name"));
                    break;
                }
                default: {
                    logger.error("Incorrect database name in database.property file");
                    throw new IllegalArgumentException("Incorrect database name in database.property file");
                }
            }
        } catch (NamingException e) {
            logger.error("Cannot create DataSource to " + dbName, e);
            throw new DataAccessException("Cannot create DataSource to " + dbName, e);
        }
    }


    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Cannot create connection to the database", e);
            throw new DataAccessException("Cannot create connection to the database", e);
        }
    }
}