package ua.pp.lazin.javajet.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;

/**
 * The type Properties loader.
 *
 * @author Ruslan Lazin
 */
public class PropertiesLoader {
    private static final Logger logger = Logger.getLogger(PropertiesLoader.class);
    private static final Properties dbProperties;
    private static final Properties mappingGET;
    private static final Properties mappingPOST;

    static {
        // Load Database properties and cache it
        dbProperties = loadPropertiesFromFile("database.properties");

        // Load  application's GET paths and cache it
        mappingGET = loadPropertiesFromFile("GETMapping.properties");

        // Load  application's POST paths and cache it
        mappingPOST = loadPropertiesFromFile("POSTMapping.properties");
    }

    private PropertiesLoader() {
    }

    /**
     * Gets db properties.
     *
     * @return the db properties
     */
    public static Properties getDBProperties() {
        return dbProperties;
    }

    /**
     * Gets mapping get.
     *
     * @return the mapping get
     */
    public static Properties getMappingGET() {
        return mappingGET;
    }

    /**
     * Gets mapping post.
     *
     * @return the mapping post
     */
    public static Properties getMappingPOST() {
        return mappingPOST;
    }


    /**
     * Load properties from file .properties.
     *
     * @param filename the filename
     * @return the properties
     */
/*
    * Method loads properties from file to java.util.Properties INSTANCE
    */
    public static Properties loadPropertiesFromFile(String filename) {

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
