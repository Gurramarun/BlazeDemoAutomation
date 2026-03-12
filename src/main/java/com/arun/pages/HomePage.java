package com.arun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

    private WebDriver driver;

    private By departureCity = By.name("fromPort");
    private By destinationCity = By.name("toPort");
    private By findFlightsBtn = By.xpath("//input[@value='Find Flights']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectDepartureCity(String city) {

        Select depart = new Select(driver.findElement(departureCity));
        depart.selectByVisibleText(city);

    }

    public void selectDestinationCity(String city) {

        Select destination = new Select(driver.findElement(destinationCity));
        destination.selectByVisibleText(city);

    }

    public void clickFindFlights() {

        driver.findElement(findFlightsBtn).click();

    }
}