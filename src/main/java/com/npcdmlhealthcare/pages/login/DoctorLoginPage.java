package com.npcdmlhealthcare.pages.login;

import com.npcdmlhealthcare.base.BasePage;
import com.npcdmlhealthcare.utils.AllureReportManager;
import com.npcdmlhealthcare.utils.LoggerUtil;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object for Doctor Login Page using PageFactory
 */
public class DoctorLoginPage extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(DoctorLoginPage.class);

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Sign in']")
    private WebElement signInButton;

    private WebDriver driver;

    public DoctorLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void enterEmail(String email) {
        try {
            emailInput.clear();
            emailInput.sendKeys(email);
            logger.info("Entered email: {}", email);
            AllureReportManager.logStep("Entered email: " + email);
        } catch (Exception e) {
            logger.error("Failed to enter email: {}", e.getMessage(), e);
            AllureReportManager.attachScreenshot(driver, "EmailInput_Failed");
            throw e;
        }
    }

    public void enterPassword(String password) {
        try {
            passwordInput.clear();
            passwordInput.sendKeys(password);
            logger.info("Entered password");
            AllureReportManager.logStep("Entered password");
          
        } catch (Exception e) {
            logger.error("Failed to enter password: {}", e.getMessage(), e);
            AllureReportManager.attachScreenshot(driver, "PasswordInput_Failed");
            throw e;
        }
    }

    public void clickSignIn() {
        try {
            signInButton.click();
            logger.info("Clicked Sign In button");
            AllureReportManager.logStep("Clicked Sign In button");
               
        } catch (Exception e) {
            logger.error("Failed to click Sign In: {}", e.getMessage(), e);
            AllureReportManager.attachScreenshot(driver, "SignInButton_Failed");
            throw e;
        }
    }

    public void loginAsDoctor(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
        
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlContains("/dashboard"));
            logger.info("Doctor successfully logged in, dashboard loaded");
            AllureReportManager.logStep("Doctor successfully logged in, dashboard loaded");
        } catch (Exception e) {
            logger.error("Dashboard not loaded after login: {}", e.getMessage(), e);
            AllureReportManager.attachScreenshot(driver, "DashboardLoad_Failed");
            throw new AssertionError("Dashboard not loaded after login", e);
        }
    }
}
