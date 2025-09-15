package com.npcdmlhealthcare.tests.login;

import com.npcdmlhealthcare.base.BasePage;
import com.npcdmlhealthcare.pages.login.PatientRegistrationPage;
import com.npcdmlhealthcare.utils.AllureReportManager;
import com.npcdmlhealthcare.utils.ConfigReader;
import com.npcdmlhealthcare.utils.LoggerUtil;
import com.npcdmlhealthcare.utils.ScreenshotUtil;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class PatientRegistrationTest extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(PatientRegistrationTest.class);
    private PatientRegistrationPage patientRegistrationPage;

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
        patientRegistrationPage = new PatientRegistrationPage(driver);
    }

    @Test(priority = 1, description = "Patient can register successfully", dependsOnGroups = "appointmentTest", groups = "patientRegistrationSuccessTest")
    
    public void patientRegistrationSuccessTest() throws InterruptedException {
        String previousUrl = driver.getCurrentUrl();
        
        patientRegistrationPage.registerPatient(patientEmail, patientPassword);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.not(ExpectedConditions.urlToBe(previousUrl)));

        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();
        logger.info("Patient redirected successfully to: " + currentUrl);
        AllureReportManager.logStep("Patient redirected successfully to: " + currentUrl);
    }

    @Test(priority = 2, description = "Patient registration fails if email already exists",
    		dependsOnMethods = "patientRegistrationSuccessTest", dependsOnGroups = "appointmentTest")
    public void patientRegistrationDuplicateEmailTest() {
        // Open registration page again
        patientRegistrationPage.openRegistrationPage();
        patientRegistrationPage.enterEmail(patientEmail);
        patientRegistrationPage.clickNext();

        // Wait for modal error box to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[@id='swal2-title']")));

        String modalText = modalTitle.getText();
        AllureReportManager.logStep("Duplicate email modal text: " + modalText);

        // Assert ignoring case
        Assert.assertTrue(modalText.equalsIgnoreCase("Email Already Registered"),
                "Expected duplicate email modal not displayed. Found: " + modalText);
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
