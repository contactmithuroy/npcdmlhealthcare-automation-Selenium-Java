package com.npcdmlhealthcare.tests.login;

import com.npcdmlhealthcare.base.BasePage;
import com.npcdmlhealthcare.pages.login.DoctorLoginPage;
import com.npcdmlhealthcare.utils.AllureReportManager;
import com.npcdmlhealthcare.utils.ConfigReader;
import com.npcdmlhealthcare.utils.LoggerUtil;
import com.npcdmlhealthcare.utils.ScreenshotUtil;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class DoctorLoginTest extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(DoctorLoginTest.class);
    private DoctorLoginPage doctorLoginPage;

    private String doctorEmail;
    private String doctorPassword;
    private String doctorUrl;

    @BeforeClass(alwaysRun = true)
    public void loadEnvironment() {
        ConfigReader.loadProperties("environment.properties");
        doctorEmail = ConfigReader.get("doctor.email");
        doctorPassword = ConfigReader.get("doctor.password");
        doctorUrl = ConfigReader.get("doctor.portal.url");
        logger.info("Environment properties loaded");
    }

    @BeforeMethod(alwaysRun = true)
    public void openDoctorPortal() {
        driver.get(doctorUrl);
        logger.info("Navigated to Doctor Portal: {}", doctorUrl);
        AllureReportManager.logStep("Navigated to Doctor Portal");
        doctorLoginPage = new DoctorLoginPage(driver);
    }

    @Test(priority = 1, description = "Verify Doctor can login successfully to dashboard")
    public void doctorLoginTest() {
        try {
            doctorLoginPage.loginAsDoctor(doctorEmail, doctorPassword);

            String currentUrl = driver.getCurrentUrl();
            logger.info("Current URL after login: {}", currentUrl);
            AllureReportManager.logStep("Current URL after login: " + currentUrl);

            Assert.assertTrue(currentUrl.contains("/dashboard"),
                    "Dashboard not loaded after login. Current URL: " + currentUrl);

        } catch (Exception e) {
            logger.error("Doctor login test failed: {}", e.getMessage(), e);
            ScreenshotUtil.captureScreenshot(driver, "DoctorLogin_Failed");
            AllureReportManager.attachScreenshot(driver, "DoctorLogin_Failed");
            Assert.fail("Doctor login test failed: " + e.getMessage());
        }
    }


    @AfterMethod(alwaysRun = true)
    public void captureFailureScreenshot(org.testng.ITestResult result) {
        if (result.getStatus() == org.testng.ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            if (screenshotPath != null) {
                AllureReportManager.attachScreenshot(driver, result.getName());
                logger.error("Test failed: {}. Screenshot captured at {}", result.getName(), screenshotPath);
            }
        }
    }
}
