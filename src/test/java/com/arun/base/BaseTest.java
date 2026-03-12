package com.arun.base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.arun.utils.DriverFactory;
import com.arun.utils.ScreenshotUtil;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverFactory.initDriver();
    }

    // Reset state before each test
    @BeforeMethod
    public void loadHomePage() {
        driver.get("https://blazedemo.com/");
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