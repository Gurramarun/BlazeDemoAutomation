package com.arun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

    private WebDriver driver;

    // Locators
    private By successMessage = By.xpath("//h1[contains(text(),'Thank you for your purchase')]");
    private By bookingId = By.xpath("//td[contains(text(),'Id')]/following-sibling::td");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Verify booking success
    public boolean isBookingSuccessful() {

        return !driver.findElements(successMessage).isEmpty();
    }

    // Get booking ID
    public String getBookingId() {

        return driver.findElement(bookingId).getText();
    }
}