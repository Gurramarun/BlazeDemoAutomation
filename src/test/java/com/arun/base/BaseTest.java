package com.arun.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import com.arun.utils.DriverFactory;
import com.arun.utils.ScreenshotUtil;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverFactory.initDriver();
    }

    @BeforeMethod
    public void loadHomePage() {
        driver.get("https://blazedemo.com/");
        // ✅ FIX: Wait for page to fully load before each test
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.name("fromPort")));
    }

    @AfterMethod
    public void captureFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtil.captureScreenshot(driver, result.getName());
        }
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}