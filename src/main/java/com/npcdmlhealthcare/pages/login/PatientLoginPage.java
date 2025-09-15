package com.npcdmlhealthcare.pages.login;

import com.npcdmlhealthcare.base.BasePage;
import com.npcdmlhealthcare.utils.AllureReportManager;
import com.npcdmlhealthcare.utils.LoggerUtil;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PatientLoginPage extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(PatientLoginPage.class);

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Sign in']")
    private WebElement signInButton;

    private WebDriver driver;

    public PatientLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void enterEmail(String email) {
        try {
            emailInput.clear();
            emailInput.sendKeys(email);
            logger.info("Entered patient email: {}", email);
            AllureReportManager.logStep("Entered patient email: " + email);
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

    public void loginAsPatient(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
