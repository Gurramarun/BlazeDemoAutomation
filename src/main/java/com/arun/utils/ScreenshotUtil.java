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

        TakesScreenshot ts = (TakesScreenshot) driver;

        File src = ts.getScreenshotAs(OutputType.FILE);

        // Timestamp for unique screenshot names
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";

        File dest = new File(path);

        dest.getParentFile().mkdirs();

        try {

            FileUtils.copyFile(src, dest);

            System.out.println("Screenshot saved at: " + path);

        } catch (IOException e) {

            e.printStackTrace();

        }

        return path;
    }
}