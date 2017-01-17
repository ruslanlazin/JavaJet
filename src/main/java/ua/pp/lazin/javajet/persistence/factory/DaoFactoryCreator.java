package ua.pp.lazin.javajet.persistence.factory;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.util.PropertiesLoader;

import java.util.Properties;

/**
 * The Daofactory creator.
 *
 * @author Ruslan Lazin
 */
public class DaoFactoryCreator {
    private static DaoFactory daoFactory;
    private final static Logger logger = Logger.getLogger(DaoFactoryCreator.class);
    private final static String DB_PROPERTY = "db.name";
    private final static String MYSQL = "mysql";
    private final static String POSTGRESQL = "postgresql";
    private final static String INVALID_DB =
            " - Incorrect database name in database.property file";

    private DaoFactoryCreator() {
    }

    /**
     * Gets factory.
     *
     * @return the factory
     */
    public static synchronized DaoFactory getFactory() {
        if (daoFactory == null) {
            daoFactory = createFactory();
        }
        return daoFactory;
    }

    /**
     *
     * @return DaoFactory of type selected in db.properties
     */
    private static DaoFactory createFactory() {
        Properties properties = PropertiesLoader.getDBProperties();
        String dbName = properties.getProperty(DB_PROPERTY).toLowerCase();
        switch (dbName) {
            case MYSQL: {
                logger.debug("Creating MySqlDaoFactory");
                return new MysqlDaoFactory();
            }
            case POSTGRESQL: {
                logger.debug("Creating PostgresqlDaoFactory");
                return new PostgresqlDaoFactory();
            }
            default: {
                logger.error(dbName + INVALID_DB);
                throw new IllegalArgumentException(dbName + INVALID_DB);
            }
        }
    }
}



