package com.npcdmlhealthcare.tests.login;

import com.npcdmlhealthcare.base.BasePage;
import com.npcdmlhealthcare.pages.login.PatientLoginPage;
import com.npcdmlhealthcare.utils.AllureReportManager;
import com.npcdmlhealthcare.utils.ConfigReader;
import com.npcdmlhealthcare.utils.LoggerUtil;
import com.npcdmlhealthcare.utils.ScreenshotUtil;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

public class PatientLoginTest extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(PatientLoginTest.class);
    private PatientLoginPage patientLoginPage;

    private String patientEmail;
    private String patientPassword;
    private String patientPortalUrl;

    @BeforeClass(alwaysRun = true)
    public void loadEnvironment() {
        ConfigReader.loadProperties("environment.properties");
        patientEmail = ConfigReader.get("patient.email");
        patientPassword = ConfigReader.get("patient.password");
        patientPortalUrl = ConfigReader.get("patient.portal.url");
        logger.info("Patient environment properties loaded");
    }

    @BeforeMethod(alwaysRun = true)
    public void openPatientPortal() {
        driver.get(patientPortalUrl);
        logger.info("Navigated to Patient Portal: {}", patientPortalUrl);
        AllureReportManager.logStep("Navigated to Patient Portal");
        patientLoginPage = new PatientLoginPage(driver);
    }

    @Test(priority = 1, description = "Verify Patient can login successfully to assessment page", 
          dependsOnGroups = "patientRegistrationSuccessTest",
          groups = "patientLoginTest")
    public void patientLoginTest() {

        patientLoginPage.loginAsPatient(patientEmail, patientPassword);

        String expectedPath = "/assessment/rom-exercise/";
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> d.getCurrentUrl().startsWith(patientPortalUrl + expectedPath));

        String currentUrl = driver.getCurrentUrl();
        logger.info("Patient redirected to assessment URL: {}", currentUrl);
        AllureReportManager.logStep("Patient redirected to assessment URL: " + currentUrl);

        // Assert.assertTrue(currentUrl.startsWith(patientPortalUrl + expectedPath),"Patient did not reach the correct assessment page. Current URL: " + currentUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void captureFailureScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            if (screenshotPath != null) {
                AllureReportManager.attachScreenshot(driver, result.getName());
                logger.error("Test failed: {}. Screenshot captured at {}", result.getName(), screenshotPath);
            }
        }
    }
}
