package com.npcdmlhealthcare.tests.appointmentmail;

import com.npcdmlhealthcare.pages.appointmentmail.AppointmentEmailPage;
import com.npcdmlhealthcare.base.BasePage;
import com.npcdmlhealthcare.utils.AllureReportManager;
import com.npcdmlhealthcare.utils.ConfigReader;
import com.npcdmlhealthcare.utils.LoggerUtil;
import com.npcdmlhealthcare.utils.ScreenshotUtil;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * AppointmentEmailTest
 * Verifies patient receives appointment mail and "Complete Assessment" link works
 */
public class AppointmentEmailTest extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(AppointmentEmailTest.class);
    private AppointmentEmailPage mailCheckPage;

    private String patientEmail;
    private String yopmail;

    @BeforeClass(alwaysRun = true)
    public void loadEnvironment() {
        ConfigReader.loadProperties("environment.properties");
        patientEmail = ConfigReader.get("patient.email");
        yopmail = ConfigReader.get("yopmail.url");
        logger.info("Appointment mail check environment loaded");
    }

    @BeforeMethod(alwaysRun = true)
    public void openYopmail() {
        mailCheckPage = new AppointmentEmailPage(driver);
        mailCheckPage.openYopmail(yopmail);
    }

    @Test(priority = 1, description = "Verify appointment mail and Complete Assessment button")
    public void verifyAppointmentMailTest() {
        mailCheckPage.enterEmail(patientEmail);
        mailCheckPage.clickCheckInbox();
        mailCheckPage.openFirstMail();

        boolean buttonPresent = mailCheckPage.isCompleteAssessmentPresent();
        Assert.assertTrue(buttonPresent, "Complete Assessment button should be present in the mail");

        // Optionally click
        mailCheckPage.clickCompleteAssessment();
        AllureReportManager.logStep("Complete Assessment button verified and clicked");
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
