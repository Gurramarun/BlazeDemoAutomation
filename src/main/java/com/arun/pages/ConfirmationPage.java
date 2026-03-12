package com.arun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ConfirmationPage {

    WebDriver driver;
    WebDriverWait wait;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // ✅ increased from 15 to 20
    }

    By successMessage = By.xpath("//h1[contains(text(),'Thank you for your purchase')]");

    public boolean isBookingSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return driver.findElements(successMessage).size() > 0;
        } catch (Exception e) {
            System.out.println("isBookingSuccessful failed: " + e.getMessage());
            return false;
        }
    }

    public String getBookingId() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));

        // Strategy 1: Find row where first cell is "Id:"
        try {
            List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (cells.size() >= 2 && cells.get(0).getText().trim().equalsIgnoreCase("Id:")) {
                    return cells.get(1).getText().trim();
                }
            }
        } catch (Exception e1) {
            System.out.println("Strategy 1 failed: " + e1.getMessage());
        }

        // Strategy 2: Explicit tbody
        try {
            return driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]")).getText().trim();
        } catch (Exception e2) {
            System.out.println("Strategy 2 failed: " + e2.getMessage());
        }

        // Strategy 3: Any second cell in any row
        try {
            return driver.findElement(By.xpath("(//table//td[2])[1]")).getText().trim();
        } catch (Exception e3) {
            System.out.println("Strategy 3 failed: " + e3.getMessage());
        }

        return "BOOKING_ID_NOT_FOUND";
    }
}