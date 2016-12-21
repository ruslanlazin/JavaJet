package ua.pp.lazin.javajet.persistence.factory;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.util.PropertiesLoader;

import java.util.Properties;

/**
 * @author Ruslan Lazin
 *
 *
 */
public class DaoFactoryCreator {
    private static DaoFactory daoFactory;
    private final static Logger logger = Logger.getLogger(DaoFactoryCreator.class);

    private DaoFactoryCreator() {
    }

    public static synchronized DaoFactory getFactory() {
        if (daoFactory == null) {
            daoFactory = createFactory();
        }
        return daoFactory;
    }

    private static DaoFactory createFactory() {
        Properties properties = PropertiesLoader.getDBProperties();
        String dbName = properties.getProperty("db.name").toLowerCase();
        switch (dbName) {
            case "mysql": {
                logger.debug("Creating MySqlDaoFactory");
                return new MysqlDaoFactory();
            }
            case "postgresql": {
                logger.debug("Creating PostgresqlDaoFactory");
                return new PostgresqlDaoFactory();
            }
            default: {
                logger.error("Incorrect database name in database.property file");
                throw new IllegalArgumentException("Incorrect database name in database.property file");
            }
        }
    }
}



