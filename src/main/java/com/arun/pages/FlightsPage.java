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

    By flightsTable = By.xpath("//table[@class='table']");
    By chooseFlightButtons = By.xpath("//input[@value='Choose This Flight']");

    public boolean isFlightsDisplayed() {

        List<WebElement> table = driver.findElements(flightsTable);
        return table.size() > 0 && table.get(0).isDisplayed();

    }

    public void chooseFirstFlight() {

        List<WebElement> flights = driver.findElements(chooseFlightButtons);

        if (flights.size() > 0) {
            flights.get(0).click();
        }

    }
}