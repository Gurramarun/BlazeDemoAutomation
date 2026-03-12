package com.arun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PurchasePage {

    private WebDriver driver;

    // Locators
    private By name = By.id("inputName");
    private By address = By.id("address");
    private By city = By.id("city");
    private By state = By.id("state");
    private By zipCode = By.id("zipCode");

    @SuppressWarnings("unused")
	private By cardType = By.id("cardType");
    private By creditCardNumber = By.id("creditCardNumber");
    private By creditCardMonth = By.id("creditCardMonth");
    private By creditCardYear = By.id("creditCardYear");

    private By nameOnCard = By.id("nameOnCard");

    private By purchaseFlightBtn = By.xpath("//input[@value='Purchase Flight']");

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
    }

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