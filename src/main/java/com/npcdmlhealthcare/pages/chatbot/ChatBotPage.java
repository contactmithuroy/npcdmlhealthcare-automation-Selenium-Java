 
package com.npcdmlhealthcare.pages.chatbot;

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

/**
 * Page Object for ChatBot interactions
 */
public class ChatBotPage extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(ChatBotPage.class);
    private WebDriverWait wait;

    // Example locators — update when actual locators are ready
    @FindBy(xpath = "//div[contains(@class,'chatbot')]")
    private WebElement chatBotContainer;

    @FindBy(xpath = "//input[@placeholder='Type your message']")
    private WebElement chatInput;

    @FindBy(xpath = "//button[text()='Send']")
    private WebElement sendButton;

    public ChatBotPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Open ChatBot container
    public void openChatBot() {
        wait.until(ExpectedConditions.visibilityOf(chatBotContainer));
        logger.info("ChatBot container opened");
        AllureReportManager.logStep("ChatBot container opened");
    }

    // Enter message/input in ChatBot
    public void enterMessage(String message) {
        wait.until(ExpectedConditions.visibilityOf(chatInput)).clear();
        chatInput.sendKeys(message);
        logger.info("Entered message in ChatBot: {}", message);
        AllureReportManager.logStep("Entered message in ChatBot: " + message);
    }

    // Click Send button
    public void clickSend() {
        wait.until(ExpectedConditions.elementToBeClickable(sendButton)).click();
        logger.info("Clicked Send button in ChatBot");
        AllureReportManager.logStep("Clicked Send button in ChatBot");
    }

    // Placeholder for future step interactions
    public void performNextStep(String input) {
        openChatBot();
        enterMessage(input);
        clickSend();
        logger.info("Performed next ChatBot step with input: {}", input);
        AllureReportManager.logStep("Performed next ChatBot step with input: " + input);
    }
}
