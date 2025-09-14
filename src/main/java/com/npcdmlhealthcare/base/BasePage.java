package com.npcdmlhealthcare.base;

import com.npcdmlhealthcare.utils.ConfigReader;
import com.npcdmlhealthcare.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BasePage {

    private static final Logger logger = LoggerUtil.getLogger(BasePage.class);
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        try {
            logger.info("========== Starting Test Setup ==========");

            ConfigReader.loadProperties("environment.properties");
            String browserName = ConfigReader.get("browser");
            
            if (browserName == null || browserName.isEmpty()) {
                throw new IllegalArgumentException("Browser not specified in environment.properties");
            }
            logger.info("Browser selected from environment.properties: {}", browserName);

            // Initialize WebDriver
            DriverFactory.initDriver(browserName);
            driver = DriverFactory.getDriver();

            logger.info("========== Test Setup Completed ==========");

        } catch (Exception e) {
            logger.error("Error during test setup: {}", e.getMessage(), e);
            throw e;
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            logger.info("========== Starting Test Teardown ==========");
            DriverFactory.quitDriver();
            logger.info("========== Test Teardown Completed ==========");
        } catch (Exception e) {
            logger.error("Error during test teardown: {}", e.getMessage(), e);
            throw e;
        }
    }
}
