package com.arun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PurchasePage {

    WebDriver driver;

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By name = By.id("inputName");
    By address = By.id("address");
    By city = By.id("city");
    By state = By.id("state");
    By zipCode = By.id("zipCode");

    By cardType = By.id("cardType");
    By creditCardNumber = By.id("creditCardNumber");
    By creditCardMonth = By.id("creditCardMonth");
    By creditCardYear = By.id("creditCardYear");

    By nameOnCard = By.id("nameOnCard");

    By purchaseFlightBtn = By.xpath("//input[@value='Purchase Flight']");

    // Enter Passenger Details
    public void enterPassengerDetails(String passengerName, String addr, String cityName, String stateName, String zip) {

        driver.findElement(name).sendKeys(passengerName);
        driver.findElement(address).sendKeys(addr);
        driver.findElement(city).sendKeys(cityName);
        driver.findElement(state).sendKeys(stateName);
        driver.findElement(zipCode).sendKeys(zip);

    }

    // Enter Payment Details
    public void enterPaymentDetails(String cardNum, String month, String year, String cardHolderName) {

        driver.findElement(creditCardNumber).sendKeys(cardNum);
        driver.findElement(creditCardMonth).clear();
        driver.findElement(creditCardMonth).sendKeys(month);
        driver.findElement(creditCardYear).clear();
        driver.findElement(creditCardYear).sendKeys(year);
        driver.findElement(nameOnCard).sendKeys(cardHolderName);

    }

    // Click Purchase Flight
    public void clickPurchaseFlight() {

        driver.findElement(purchaseFlightBtn).click();

    }
}