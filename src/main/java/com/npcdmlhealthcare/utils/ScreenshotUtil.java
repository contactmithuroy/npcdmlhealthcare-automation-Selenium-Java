package com.npcdmlhealthcare.utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility to capture screenshots
 */
public final class ScreenshotUtil {

    private static final Logger logger = LoggerUtil.getLogger(ScreenshotUtil.class);

    private ScreenshotUtil() {
        // Prevent instantiation
    }

    /**
     * Capture screenshot and save to root screenshots directory.
     * @param driver WebDriver instance
     * @param testName Name of test or step
     * @return Screenshot file path
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = testName + "_" + timestamp + ".png";
            String screenshotDir = System.getProperty("user.dir") + "/screenshots/";
            File dir = new File(screenshotDir);
            if (!dir.exists()) dir.mkdirs();

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String filePath = screenshotDir + screenshotName;
            File destination = new File(filePath);
            org.openqa.selenium.io.FileHandler.copy(screenshotFile, destination);

            logger.info("Screenshot captured: {}", filePath);
            return filePath;

        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage(), e);
            return null;
        }
    }
}
 
