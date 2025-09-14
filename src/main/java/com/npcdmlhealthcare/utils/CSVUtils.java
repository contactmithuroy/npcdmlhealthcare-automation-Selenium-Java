package com.npcdmlhealthcare.utils;

import com.npcdmlhealthcare.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading CSV test data.
 */
public class CSVUtils {

    private static final Logger logger = LoggerUtil.getLogger(CSVUtils.class);

    private CSVUtils() {
        // Prevent instantiation
    }

    public static List<String[]> readCSV(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            logger.info("📂 Reading CSV file: {}", filePath);

            String line;
            while ((line = br.readLine()) != null) {
                records.add(line.split(","));
            }

            logger.info("📊 Loaded {} rows from CSV file.", records.size());

        } catch (Exception e) {
            logger.error("⚠️ Failed to read CSV file {}: {}", filePath, e.getMessage(), e);
        }
        return records;
    }
}
