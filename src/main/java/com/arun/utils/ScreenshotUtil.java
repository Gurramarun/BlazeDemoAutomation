package com.arun.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {

        // ✅ FIX: null check to avoid NullPointerException if driver crashed
        if (driver == null) {
            System.out.println("Screenshot skipped: driver is null for test - " + testName);
            return null;
        }

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";

            File dest = new File(path);
            dest.getParentFile().mkdirs(); // ✅ creates /screenshots folder if missing

            FileUtils.copyFile(src, dest);
            System.out.println("Screenshot saved at: " + path);

            return path;

        } catch (IOException e) {
            // ✅ FIX: log clearly instead of just stack trace
            System.out.println("Failed to save screenshot for test: " + testName);
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            // ✅ FIX: catch any other unexpected errors (e.g. driver disconnected)
            System.out.println("Unexpected error taking screenshot for test: " + testName);
            e.printStackTrace();
            return null;
        }
    }
}