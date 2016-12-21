package ua.pp.lazin.javajet.util;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;

/**
 * @author Ruslan Lazin
 */
public class PropertiesLoader {
    private static final Logger logger = Logger.getLogger(PropertiesLoader.class);
    private static final Properties dbProperties;

    static {
        dbProperties = new Properties();
        try {
            dbProperties.load(DaoFactoryCreator.class.getClassLoader()
                                            .getResourceAsStream("database.properties"));
        } catch (IOException e) {
            logger.error("Cannot load database.properties", e);
            throw new UncheckedIOException("Cannot load database.properties", e);
        } catch (IllegalArgumentException e) {
            logger.error("database.properties contains a malformed Unicode escape sequence", e);
            throw e;
        }
    }

    public static Properties getDBProperties() {
        return dbProperties;
    }
}
