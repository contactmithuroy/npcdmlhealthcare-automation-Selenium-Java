package com.npcdmlhealthcare.base;

import com.npcdmlhealthcare.utils.LoggerUtil;
import com.npcdmlhealthcare.utils.CSVUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.List;

public abstract class BaseTest {

    private static final Logger logger = LoggerUtil.getLogger(BaseTest.class);
    protected WebDriver driver;
    
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        try {
            logger.info("========== Starting Test Setup ==========");

            // Read browser from CSV
            String csvFilePath = "src/main/resources/testdata/BrowserConfig.csv";
            List<String[]> data = CSVUtils.readCSV(csvFilePath);
            String browserName = data.get(1)[0]; // assuming 1st row is header, 2nd row has browser

            logger.info("Browser selected from CSV: {}", browserName);

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
        logger.info("========== Starting Test Teardown ==========");
        DriverFactory.quitDriver();
        logger.info("========== Test Teardown Completed ==========");
    }
}
