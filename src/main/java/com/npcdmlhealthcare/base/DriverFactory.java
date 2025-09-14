package com.npcdmlhealthcare.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.npcdmlhealthcare.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
public class DriverFactory {

    private static final Logger logger =LoggerUtil.getLogger(DriverFactory.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
        // Prevent instantiation
    }

    public static void initDriver(String browserName) {
        if (driver.get() == null) {
            logger.info("🚀 Initializing WebDriver for browser: {}", browserName);

            try {
                switch (browserName.toLowerCase()) {
                    case "chrome":
                        driver.set(new ChromeDriver());
                        break;
                    case "firefox":
                        driver.set(new FirefoxDriver());
                        break;
                    case "edge":
                        driver.set(new EdgeDriver());
                        break;
                    default:
                        logger.error("❌ Unsupported browser: {}", browserName);
                        throw new IllegalArgumentException("Invalid browser: " + browserName);
                }

                driver.get().manage().window().maximize();
                driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                logger.info("✅ {} browser launched successfully.", browserName);

            } catch (Exception e) {
                logger.error("🔥 Error while initializing WebDriver for {}: {}", browserName, e.getMessage(), e);
                throw e;
            }
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            logger.info("🛑 Closing WebDriver session...");
            driver.get().quit();
            driver.remove();
            logger.info("👋 WebDriver closed and cleaned up.");
        }
    }
}
