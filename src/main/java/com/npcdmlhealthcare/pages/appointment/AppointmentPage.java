package com.npcdmlhealthcare.pages.appointment;

import com.npcdmlhealthcare.base.BasePage;
import com.npcdmlhealthcare.utils.AllureReportManager;
import com.npcdmlhealthcare.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class AppointmentPage extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(AppointmentPage.class);
    private WebDriverWait wait;

    public AppointmentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//button[@type='button' and contains(@class, 'ant-btn-block')]")
    private WebElement appointmentLogo;

    @FindBy(id = "name")
    private WebElement patientNameInput;

    @FindBy(xpath = "//input[@placeholder='Select or type patient email']")
    private WebElement patientEmailInput;

    @FindBy(xpath = "(//div[@class='ant-select-selector'])[3]")
    private WebElement bodyRegionDropdown;

    @FindBy(xpath = "//div[@role='option']")
    private List<WebElement> bodyRegionOptions;

    @FindBy(id = "date")
    private WebElement appointmentDateInput;

    @FindBy(xpath = "//a[normalize-space()='Today']")
    private WebElement todayButton;
    
    @FindBy(xpath = "//input[@placeholder='Start Time']")
    private WebElement appointmentTimeInput;

    @FindBy(xpath ="//span[text()='Submit']")
    private WebElement submitButton;

    @FindBy(xpath ="//button[contains(text(),'Cancel')]")
    private WebElement cancelButton;

    // Actions
    public void clickAppointmentLogo() {
        try {
            wait.until(ExpectedConditions.visibilityOf(appointmentLogo));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", appointmentLogo);
            logger.info("Clicked Appointment Logo via JS");
            AllureReportManager.logStep("Clicked Appointment Logo via JS");
        } catch (Exception e) {
            logger.error("Failed to click Appointment Logo via JS: {}", e.getMessage(), e);
            AllureReportManager.attachScreenshot(driver, "AppointmentLogo_JS_ClickFailed");
            throw e;
        }
    }

    public void enterPatientName(String name) {
        wait.until(ExpectedConditions.visibilityOf(patientNameInput)).sendKeys(name);
        logger.info("Entered patient name: {}", name);
        AllureReportManager.logStep("Entered patient name: " + name);
    }

    public void enterPatientEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(patientEmailInput)).sendKeys(email);
        logger.info("Entered patient email: {}", email);
        AllureReportManager.logStep("Entered patient email: " + email);
    }

    public void selectBodyRegions() {
        try {
        	bodyRegionDropdown.click();

            Actions actions = new Actions(driver);
            // Select Neck
            actions.sendKeys(Keys.ENTER).perform();
            // Select UE
            actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
            // Select Back
            actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
            // Select LE
            actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
            // Close dropdown
            actions.sendKeys(Keys.ESCAPE).perform();

            logger.info("Selected body regions: Neck, UE, Back, LE");
            AllureReportManager.logStep("Selected body regions: Neck, UE, Back, LE");

        } catch (Exception e) {
            logger.error("Failed to select body regions: {}", e.getMessage(), e);
            AllureReportManager.attachScreenshot(driver, "SelectBodyRegion_Failed");
            throw e;
        }
    }


    public void selectTodayDate() {
        wait.until(ExpectedConditions.elementToBeClickable(appointmentDateInput)).click();
        wait.until(ExpectedConditions.elementToBeClickable(todayButton)).click();

        logger.info("Selected appointment date as Today");
        AllureReportManager.logStep("Selected appointment date as Today");
    }

    public void selectAppointmentTime() {
        wait.until(ExpectedConditions.elementToBeClickable(appointmentTimeInput)).click();

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();

        logger.info("Selected appointment time");
        AllureReportManager.logStep("Selected appointment time");
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        new WebDriverWait(driver, Duration.ofSeconds(5));
        logger.info("Clicked Submit button");
        AllureReportManager.logStep("Clicked Submit button");
    }

    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
        logger.info("Clicked Cancel button");
        AllureReportManager.logStep("Clicked Cancel button");
    }

}
