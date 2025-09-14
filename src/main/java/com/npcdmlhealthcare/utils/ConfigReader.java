package com.npcdmlhealthcare.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ConfigReader {

    private static Properties properties;

    private ConfigReader() {
        // Prevent instantiation
    }

    public static void loadProperties(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties = new Properties();
            properties.load(fis);
            LoggerUtil.getLogger(ConfigReader.class)
                      .info("Loaded environment properties from: {}", filePath);
        } catch (IOException e) {
            LoggerUtil.getLogger(ConfigReader.class)
                      .error("Failed to load properties file: {}", filePath, e);
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        if (properties == null) {
            throw new IllegalStateException("Properties file not loaded. Call loadProperties() first.");
        }
        return properties.getProperty(key);
    }
}
 
