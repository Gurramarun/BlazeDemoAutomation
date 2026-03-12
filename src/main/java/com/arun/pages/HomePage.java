package com.arun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

    private WebDriver driver;

    // Locators
    private By departureCity = By.name("fromPort");
    private By destinationCity = By.name("toPort");
    private By findFlightsBtn = By.xpath("//input[@value='Find Flights']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Select Departure City
    public void selectDepartureCity(String city) {

        Select depart = new Select(driver.findElement(departureCity));

        try {
            depart.selectByVisibleText(city);
        } catch (Exception e) {
            System.out.println("City not found in dropdown: " + city);
        }
    }

    // Select Destination City
    public void selectDestinationCity(String city) {

        Select destination = new Select(driver.findElement(destinationCity));

        try {
            destination.selectByVisibleText(city);
        } catch (Exception e) {
            System.out.println("City not found in dropdown: " + city);
        }
    }

    // Click Find Flights
    public void clickFindFlights() {

        driver.findElement(findFlightsBtn).click();

    }
}