package com.arun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    By successMessage = By.xpath("//h1[contains(text(),'Thank you for your purchase')]");
    By bookingId = By.xpath("//table//tr[1]/td[2]");

    public boolean isBookingSuccessful() {

        return driver.findElements(successMessage).size() > 0;

    }

    public String getBookingId() {

        return driver.findElement(bookingId).getText();

    }
}