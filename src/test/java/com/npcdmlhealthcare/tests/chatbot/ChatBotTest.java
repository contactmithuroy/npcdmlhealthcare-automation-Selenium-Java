package com.npcdmlhealthcare.tests.chatbot;

import com.npcdmlhealthcare.base.BasePage;
import com.npcdmlhealthcare.pages.login.PatientLoginPage;
import com.npcdmlhealthcare.utils.ConfigReader;
import com.npcdmlhealthcare.utils.LoggerUtil;
import com.npcdmlhealthcare.utils.ScreenshotUtil;
import com.npcdmlhealthcare.utils.AllureReportManager;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class ChatBotTest extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(ChatBotTest.class);
    private PatientLoginPage patientLoginPage;

    private String patientEmail;
    private String patientPassword;
    private String patientUrl;

    @BeforeClass(alwaysRun = true)
    public void loadEnvironment() {
        ConfigReader.loadProperties("environment.properties");
        patientEmail = ConfigReader.get("patient.email");
        patientPassword = ConfigReader.get("patient.password");
        patientUrl = ConfigReader.get("patient.portal.url");
        logger.info("Patient environment loaded for ChatBot tests");
    }

    @BeforeMethod(alwaysRun = true)
    public void openPatientPortal() {
        driver.get(patientUrl);
        patientLoginPage = new PatientLoginPage(driver);
    }

    @Test(priority = 1, description = "Patient login and force redirect to dashboard", dependsOnGroups ="patientLoginTest")
    public void patientLoginAndDashboardTest() throws InterruptedException {
        // Reuse PatientLoginPage login method
        patientLoginPage.loginAsPatient(patientEmail, patientPassword);

        // Force redirect to dashboard
        driver.get(patientUrl + "/dashboard");
        Thread.sleep(1000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/dashboard"),
                "Patient not redirected to dashboard. Current URL: " + currentUrl);

        logger.info("Patient successfully redirected to dashboard: {}", currentUrl);
        AllureReportManager.logStep("Patient successfully redirected to dashboard");
    }

    @Test(priority = 2, description = "ChatBot next test case (placeholder)")
    public void chatBotNextStepTest() {
        // Placeholder for next steps (fill input, etc.)
        logger.info("Next ChatBot step test - not implemented yet");
        AllureReportManager.logStep("Next ChatBot step test - placeholder");
    }

    @AfterMethod(alwaysRun = true)
    public void captureFailureScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtil.captureScreenshot(driver, result.getName());
            AllureReportManager.attachScreenshot(driver, result.getName());
        }
    }
}
