package com.npcdmlhealthcare.pages.appointmentmail;

import com.npcdmlhealthcare.utils.AllureReportManager;
import com.npcdmlhealthcare.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object for Appointment Mail Check (Yopmail)
 */
public class AppointmentEmailPage {

    private static final Logger logger = LoggerUtil.getLogger(AppointmentEmailPage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "login")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@title='Check Inbox @yopmail.com']")
    private WebElement checkInboxBtn;

    @FindBy(id = "ifinbox")
    private WebElement inboxFrame;

    @FindBy(xpath = "(//span[@class='lmh']/following-sibling::span)[1]")
    private WebElement firstMail;

    @FindBy(id = "ifmail")
    private WebElement mailFrame;

    @FindBy(xpath = "//a[text()[normalize-space()='Complete Assessment']]")
    private WebElement completeAssessmentBtn;

    // ✅ Constructor, same as PatientRegistrationPage
    public AppointmentEmailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        PageFactory.initElements(driver, this);
    }

    public void openYopmail(String yopmail) {
        driver.get(yopmail);
        logger.info("Opened Yopmail homepage");
        AllureReportManager.logStep("Opened Yopmail homepage");
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).clear();
        emailInput.sendKeys(email);
        logger.info("Entered patient email: {}", email);
        AllureReportManager.logStep("Entered patient email: " + email);
    }

    public void clickCheckInbox() {
        wait.until(ExpectedConditions.elementToBeClickable(checkInboxBtn)).click();
        logger.info("Clicked on Check Inbox button");
        AllureReportManager.logStep("Clicked on Check Inbox button");
    }

    public void openFirstMail() {
        driver.switchTo().frame(inboxFrame);
        wait.until(ExpectedConditions.elementToBeClickable(firstMail)).click();
        logger.info("Opened first appointment mail");
        AllureReportManager.logStep("Opened first appointment mail");
        driver.switchTo().defaultContent();
    }

    public boolean isCompleteAssessmentPresent() {
        driver.switchTo().frame(mailFrame);
        boolean present = wait.until(ExpectedConditions.visibilityOf(completeAssessmentBtn)).isDisplayed();
        driver.switchTo().defaultContent();
        logger.info("Complete Assessment button present: {}", present);
        AllureReportManager.logStep("Complete Assessment button present: " + present);
        return present;
    }

    public void clickCompleteAssessment() {
        driver.switchTo().frame(mailFrame);
        wait.until(ExpectedConditions.elementToBeClickable(completeAssessmentBtn)).click();
        logger.info("Clicked Complete Assessment button");
        AllureReportManager.logStep("Clicked Complete Assessment button");
        driver.switchTo().defaultContent();
    }
}
