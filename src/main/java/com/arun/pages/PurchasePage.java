package com.arun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PurchasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By name             = By.id("inputName");
    private By address          = By.id("address");
    private By city             = By.id("city");
    private By state            = By.id("state");
    private By zipCode          = By.id("zipCode");
    private By creditCardNumber = By.id("creditCardNumber");
    private By creditCardMonth  = By.id("creditCardMonth");
    private By creditCardYear   = By.id("creditCardYear");
    private By nameOnCard       = By.id("nameOnCard");
    private By purchaseFlightBtn = By.xpath("//input[@value='Purchase Flight']");

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterPassengerDetails(String passengerName, String addr,
                                       String cityName, String stateName, String zip) {
        // ✅ FIX: wait for form to load before filling
        wait.until(ExpectedConditions.visibilityOfElementLocated(name));

        driver.findElement(name).clear();
        driver.findElement(name).sendKeys(passengerName);

        driver.findElement(address).clear();
        driver.findElement(address).sendKeys(addr);

        driver.findElement(city).clear();
        driver.findElement(city).sendKeys(cityName);

        driver.findElement(state).clear();
        driver.findElement(state).sendKeys(stateName);

        driver.findElement(zipCode).clear();
        driver.findElement(zipCode).sendKeys(zip);

        System.out.println("Passenger details entered for: " + passengerName);
    }

    public void enterPaymentDetails(String cardNum, String month,
                                     String year, String cardHolderName) {
        // ✅ FIX: wait for payment fields to be ready
        wait.until(ExpectedConditions.visibilityOfElementLocated(creditCardNumber));

        driver.findElement(creditCardNumber).clear();
        driver.findElement(creditCardNumber).sendKeys(cardNum);

        driver.findElement(creditCardMonth).clear();
        driver.findElement(creditCardMonth).sendKeys(month);

        driver.findElement(creditCardYear).clear();
        driver.findElement(creditCardYear).sendKeys(year);

        driver.findElement(nameOnCard).clear();
        driver.findElement(nameOnCard).sendKeys(cardHolderName);

        System.out.println("Payment details entered for card holder: " + cardHolderName);
    }

    public void clickPurchaseFlight() {
        // ✅ FIX: wait for button to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(purchaseFlightBtn));
        driver.findElement(purchaseFlightBtn).click();
        System.out.println("Purchase Flight button clicked.");
    }
}