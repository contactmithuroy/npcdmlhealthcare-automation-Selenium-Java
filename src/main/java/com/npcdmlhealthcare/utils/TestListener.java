package com.npcdmlhealthcare.utils;


import com.npcdmlhealthcare.base.DriverFactory;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger logger = LoggerUtil.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("🟢 Test Started: {}", result.getName());
        AllureReportManager.logStep("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("✅ Test Passed: {}", result.getName());
        AllureReportManager.logStep("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("❌ Test Failed: {}", result.getName());
        logger.error("🔥 Reason: {}", result.getThrowable().getMessage());

        // Attach screenshot to Allure (no local saving)
        AllureReportManager.attachScreenshot(DriverFactory.getDriver(), result.getName() + "_Failure");

        // Attach failure reason as text
        AllureReportManager.attachTextLog("Failure Reason", result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("⚠️ Test Skipped: {}", result.getName());
        AllureReportManager.logStep("Test Skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("🟢 Test Suite Started: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("🟣 Test Suite Finished: {}", context.getName());
    }
}