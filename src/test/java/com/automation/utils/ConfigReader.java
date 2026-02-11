package com.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration Reader - Reads properties from config file
 * Demonstrates Singleton pattern and Encapsulation
 */
public class ConfigReader {

    private static ConfigReader instance;
    private Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    /**
     * Private constructor (Singleton pattern)
     */
    private ConfigReader() {
        properties = new Properties();
        loadProperties();
    }

    /**
     * Get singleton instance
     * @return ConfigReader instance
     */
    public static ConfigReader getInstance() {
        if (instance == null) {
            synchronized (ConfigReader.class) {
                if (instance == null) {
                    instance = new ConfigReader();
                }
            }
        }
        return instance;
    }

    /**
     * Load properties from file
     */
    private void loadProperties() {
        try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(input);
            System.out.println("Configuration loaded successfully from: " + CONFIG_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Failed to load config.properties from: " + CONFIG_FILE_PATH);
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    /**
     * Get property value by key
     * @param key property key
     * @return property value
     */
    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value.trim();
    }

    /**
     * Get property value with default
     * @param key property key
     * @param defaultValue default value if key not found
     * @return property value or default
     */
    public String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key);
        return (value != null) ? value.trim() : defaultValue;
    }
}
