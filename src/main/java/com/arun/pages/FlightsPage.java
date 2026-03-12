package com.arun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class FlightsPage {

    private WebDriver driver;

    // Locators
    private By flightsTable = By.xpath("//table[@class='table']");
    private By chooseFlightButtons = By.xpath("//input[@value='Choose This Flight']");

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Verify flights list displayed
    public boolean isFlightsDisplayed() {

        List<WebElement> table = driver.findElements(flightsTable);

        return !table.isEmpty() && table.get(0).isDisplayed();
    }

    // Choose first available flight
    public void chooseFirstFlight() {

        List<WebElement> flights = driver.findElements(chooseFlightButtons);

        if (!flights.isEmpty()) {
            flights.get(0).click();
        }

    }
}