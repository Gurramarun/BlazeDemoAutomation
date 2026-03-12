package com.arun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class FlightsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By flightsTable      = By.xpath("//table[@class='table']");
    private By chooseFlightBtns  = By.xpath("//input[@value='Choose This Flight']");

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isFlightsDisplayed() {
        try {
            // ✅ FIX: wait for table to appear before checking
            wait.until(ExpectedConditions.visibilityOfElementLocated(flightsTable));
            List<WebElement> table = driver.findElements(flightsTable);
            return table.size() > 0 && table.get(0).isDisplayed();
        } catch (Exception e) {
            System.out.println("Flights table not displayed: " + e.getMessage());
            return false;
        }
    }

    public void chooseFirstFlight() {
        // ✅ FIX: wait for buttons to appear before clicking
        wait.until(ExpectedConditions.visibilityOfElementLocated(chooseFlightBtns));
        List<WebElement> flights = driver.findElements(chooseFlightBtns);
        if (flights.size() > 0) {
            flights.get(0).click();
            System.out.println("First flight selected. Total flights available: " + flights.size());
        } else {
            throw new RuntimeException("No flights found to choose from!");
        }
    }
}