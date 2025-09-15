package com.npcdmlhealthcare.pages.login;

import com.npcdmlhealthcare.base.BasePage;
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
 * Page Object for Patient Registration
 */
public class PatientRegistrationPage extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(PatientRegistrationPage.class);

    private WebDriverWait wait;

    // Register here link on patient portal
    @FindBy(xpath = "//a[normalize-space()='Register here']")
    private WebElement registerHereLink;

    // Email input on registration page
    @FindBy(xpath = "//input[@placeholder='Enter Email']")
    private WebElement emailInput;

    // Next button after email
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement nextButton;

    // Password input
    @FindBy(name = "password")
    private WebElement passwordInput;

    // Confirm Password input
    @FindBy(name = "confirmPassword")
    private WebElement confirmPasswordInput;

    // Register button
    @FindBy(xpath = "//button[normalize-space()='Register']")
    private WebElement registerButton;
    
    // Dashboard
    @FindBy(xpath = "(//span[@class='ant-menu-title-content']//a)[1]")
    private WebElement dashboard;
    

    public PatientRegistrationPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openRegistrationPage() {
        wait.until(ExpectedConditions.elementToBeClickable(registerHereLink)).click();
        wait.until(ExpectedConditions.urlContains("/register"));
        logger.info("Navigated to Registration page");
        AllureReportManager.logStep("Navigated to Registration page");
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
        logger.info("Entered patient email: {}", email);
        AllureReportManager.logStep("Entered patient email: " + email);
    }

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        logger.info("Clicked Next button after email");
        AllureReportManager.logStep("Clicked Next button");
    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordInput)).sendKeys(password);
        logger.info("Entered password and confirm password");
        AllureReportManager.logStep("Entered password and confirm password");
    }

    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
        logger.info("Clicked Register button");
        AllureReportManager.logStep("Clicked Register button");
    }

    public void registerPatient(String email, String password) {
        openRegistrationPage();
        enterEmail(email);
        clickNext();
        setPassword(password);
        clickRegister();
    }

}
