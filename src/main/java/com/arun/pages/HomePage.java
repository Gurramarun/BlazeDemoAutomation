package com.arun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By departureCity  = By.name("fromPort");
    private By destinationCity = By.name("toPort");
    private By findFlightsBtn  = By.xpath("//input[@value='Find Flights']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectDepartureCity(String city) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(departureCity));
        Select depart = new Select(driver.findElement(departureCity));
        printDropdownOptions("Departure", depart);
        selectOption(depart, city);
    }

    public void selectDestinationCity(String city) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(destinationCity));
        Select destination = new Select(driver.findElement(destinationCity));
        printDropdownOptions("Destination", destination);
        selectOption(destination, city);
    }

    public void clickFindFlights() {
        wait.until(ExpectedConditions.elementToBeClickable(findFlightsBtn));
        driver.findElement(findFlightsBtn).click();
    }

    // ✅ FIX: loop directly through options instead of selectByVisibleText
    // This avoids issues when the option is already selected (e.g. "Paris" as default)
    private void selectOption(Select select, String city) {
        List<WebElement> options = select.getOptions();

        // Step 1: Exact match
        for (WebElement option : options) {
            if (option.getText().trim().equals(city)) {
                option.click();
                System.out.println("Selected (exact): " + option.getText());
                return;
            }
        }

        // Step 2: Case-insensitive match
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(city)) {
                option.click();
                System.out.println("Selected (case-insensitive): " + option.getText());
                return;
            }
        }

        // Step 3: Partial match
        for (WebElement option : options) {
            if (option.getText().trim().contains(city) || city.contains(option.getText().trim())) {
                option.click();
                System.out.println("Selected (partial): " + option.getText());
                return;
            }
        }

        throw new RuntimeException("Could not find dropdown option for: '" + city + "'");
    }

    private void printDropdownOptions(String label, Select select) {
        System.out.println("=== " + label + " dropdown options ===");
        for (WebElement option : select.getOptions()) {
            System.out.println("  [" + option.getAttribute("value") + "] -> '" + option.getText() + "'");
        }
    }
}