package com.npcdmlhealthcare.tests.appointment;

import com.npcdmlhealthcare.base.BasePage;
import com.npcdmlhealthcare.pages.appointment.AppointmentPage;
import com.npcdmlhealthcare.pages.login.DoctorLoginPage;
import com.npcdmlhealthcare.utils.AllureReportManager;
import com.npcdmlhealthcare.utils.ConfigReader;
import com.npcdmlhealthcare.utils.LoggerUtil;
import com.npcdmlhealthcare.utils.ScreenshotUtil;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class AppointmentTest extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(AppointmentTest.class);
    private DoctorLoginPage doctorLoginPage;
    private AppointmentPage appointmentPage;

    private String doctorEmail;
    private String doctorPassword;
    private String doctorUrl;
    private String patientEmail;

    @BeforeClass(alwaysRun = true)
    public void loadEnvironment() {
        ConfigReader.loadProperties("environment.properties");
        doctorEmail = ConfigReader.get("doctor.email");
        doctorPassword = ConfigReader.get("doctor.password");
        doctorUrl = ConfigReader.get("doctor.portal.url");
        
        patientEmail = ConfigReader.get("patient.email");
    }

    @BeforeMethod(alwaysRun = true)
    public void openDoctorPortal() {
        driver.get(doctorUrl);
        doctorLoginPage = new DoctorLoginPage(driver);
        appointmentPage = new AppointmentPage(driver);
        doctorLoginPage.loginAsDoctor(doctorEmail, doctorPassword);
    }

    @Test(description = "Create new appointment for patient")
    public void createAppointmentTest() {
        appointmentPage.clickAppointmentLogo();
        appointmentPage.enterPatientName("Test Patient");
        appointmentPage.enterPatientEmail(patientEmail);
        appointmentPage.selectBodyRegions();
        appointmentPage.selectTodayDate();
        appointmentPage.selectAppointmentTime();
        appointmentPage.clickSubmit();
        AllureReportManager.logStep("Appointment created successfully for Test Patient");
    }

    @AfterMethod(alwaysRun = true)
    public void captureFailureScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtil.captureScreenshot(driver, result.getName());
            AllureReportManager.attachScreenshot(driver, result.getName());
        }
    }
}
