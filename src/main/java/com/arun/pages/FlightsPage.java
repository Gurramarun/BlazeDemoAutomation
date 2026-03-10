package com.arun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class FlightsPage {

    WebDriver driver;

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By flightsTable = By.xpath("//table[@class='table']");
    By chooseFlightButtons = By.xpath("//input[@value='Choose This Flight']");

    // Verify flights list displayed
    public boolean isFlightsDisplayed() {

        return driver.findElement(flightsTable).isDisplayed();

    }

    // Choose first flight
    public void chooseFirstFlight() {

        List<WebElement> flights = driver.findElements(chooseFlightButtons);

        if (flights.size() > 0) {
            flights.get(0).click();
        }

    }
}