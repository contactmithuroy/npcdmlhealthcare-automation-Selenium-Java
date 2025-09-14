package com.npcdmlhealthcare.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Logger utility class for centralized logging using Log4j2.
 * Provides reusable and thread-safe logger instances.
 */
public final class LoggerUtil {

    private LoggerUtil() {
        // Prevent instantiation
    }

    /**
     * Get a logger instance for the given class.
     *
     * @param clazz Class where the logger will be used.
     * @return Logger instance.
     */
    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }
}
 
