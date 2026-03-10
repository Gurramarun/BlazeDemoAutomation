package com.arun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By departureCity = By.name("fromPort");
    By destinationCity = By.name("toPort");
    By findFlightsBtn = By.xpath("//input[@value='Find Flights']");

    // Select Departure City
    public void selectDepartureCity(String city) {

        Select depart = new Select(driver.findElement(departureCity));
        depart.selectByVisibleText(city);

    }

    // Select Destination City
    public void selectDestinationCity(String city) {

        Select destination = new Select(driver.findElement(destinationCity));
        destination.selectByVisibleText(city);

    }

    // Click Find Flights
    public void clickFindFlights() {

        driver.findElement(findFlightsBtn).click();

    }
}