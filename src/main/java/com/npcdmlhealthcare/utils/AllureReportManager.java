package com.npcdmlhealthcare.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public final class AllureReportManager {

    private static final Logger logger = LoggerUtil.getLogger(AllureReportManager.class);

    private AllureReportManager() {
        // Prevent instantiation
    }

    @Attachment(value = "{name}", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver, String name) {
        try {
            logger.info("📸 Capturing screenshot for Allure: {}", name);
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            logger.error("⚠️ Failed to capture screenshot: {}", e.getMessage(), e);
            return new byte[0];
        }
    }

    @Attachment(value = "{name}", type = "text/plain")
    public static String attachTextLog(String name, String msg) {
        logger.info("📝 Attaching log to Allure: {} -> {}", name, msg);
        return msg;
    }

    @Attachment(value = "Page Source", type = "text/html")
    public static byte[] attachPageSource(WebDriver driver) {
        try {
            logger.info("📄 Attaching page source to Allure report.");
            return driver.getPageSource().getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("⚠️ Failed to attach page source: {}", e.getMessage(), e);
            return new byte[0];
        }
    }


    public static void logStep(String stepMessage) {
        logger.info("🔖 Step: {}", stepMessage);
        Allure.addAttachment("Step Log", new ByteArrayInputStream(stepMessage.getBytes(StandardCharsets.UTF_8)));
    }
}
