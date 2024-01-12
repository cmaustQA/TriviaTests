package org.opentdbtest.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * This class is used to read properties from a properties file.
 */
public class PropertyReader {

    // Path to the properties file
    private static final String PROPERTIES_FILE_PATH = "src/test/resources/app.properties";

    /**
     * This method is used to get the value of a property from the properties file.
     *
     * @param propertyKey The key of the property.
     * @return The value of the property.
     * @throws RuntimeException if an error occurs while reading from the properties file.
     */
    public String getPropertyValue(String propertyKey) {
        Properties prop = new Properties();
        try {
            prop.load(Files.newBufferedReader(Paths.get(PROPERTIES_FILE_PATH)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from property file", e);
        }
        return prop.getProperty(propertyKey);
    }
}