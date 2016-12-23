package ua.pp.lazin.javajet.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;

/**
 * @author Ruslan Lazin
 */
public class PropertiesLoader {
    private static final Logger logger = Logger.getLogger(PropertiesLoader.class);
    private static final Properties dbProperties;
    private static final Properties mappingGET;
    private static final Properties mappingPOST;

    static {
        // Load Database properties
        dbProperties = loadPropertiesFromFile("database.properties");

        // Load  application's GET paths
        mappingGET = loadPropertiesFromFile("GETMapping.properties");

        // Load  application's POST paths
        mappingPOST = loadPropertiesFromFile("POSTMapping.properties");
    }


    public static Properties getDBProperties() {
        return dbProperties;
    }

    public static Properties getMappingGET() {
        return mappingGET;
    }

    public static Properties getMappingPOST() {
        return mappingPOST;
    }


    /*
    * Method loads properties from file to java.util.Properties INSTANCE
    */
    private static Properties loadPropertiesFromFile(String filename) {

        Properties properties = new Properties();
        try {
            properties.load(PropertiesLoader.class.getClassLoader()
                    .getResourceAsStream(filename));
        } catch (IOException e) {
            logger.error("Cannot load " + filename, e);
            throw new UncheckedIOException("Cannot load " + filename, e);
        } catch (IllegalArgumentException e) {
            logger.error(filename + " contains a malformed Unicode escape sequence", e);
            throw e;
        }
        return properties;
    }
}
