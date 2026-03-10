package com.arun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By confirmationMessage = By.xpath("//h1[text()='Thank you for your purchase today!']");
    By idNumber = By.xpath("//td[text()='Id']/following-sibling::td");

    // Verify confirmation message
    public boolean isBookingSuccessful() {

        return driver.findElement(confirmationMessage).isDisplayed();

    }

    // Get Booking ID
    public String getBookingId() {

        return driver.findElement(idNumber).getText();

    }
}