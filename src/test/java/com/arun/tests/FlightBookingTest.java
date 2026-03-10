package com.arun.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.arun.base.BaseTest;
import com.arun.pages.HomePage;
import com.arun.pages.FlightsPage;
import com.arun.pages.PurchasePage;
import com.arun.pages.ConfirmationPage;

public class FlightBookingTest extends BaseTest {

    @Test
    public void bookFlightTest() {

        // Home Page
        HomePage home = new HomePage(driver);

        home.selectDepartureCity("Boston");
        home.selectDestinationCity("London");
        home.clickFindFlights();

        // Flights Page
        FlightsPage flights = new FlightsPage(driver);

        Assert.assertTrue(flights.isFlightsDisplayed(), "Flights list not displayed");

        flights.chooseFirstFlight();

        // Purchase Page
        PurchasePage purchase = new PurchasePage(driver);

        purchase.enterPassengerDetails(
                "Arun",
                "Hyderabad Street",
                "Hyderabad",
                "Telangana",
                "500001"
        );

        purchase.enterPaymentDetails(
                "1234567890123456",
                "12",
                "2028",
                "Arun Gurram"
        );

        purchase.clickPurchaseFlight();

        // Confirmation Page
        ConfirmationPage confirm = new ConfirmationPage(driver);

        Assert.assertTrue(confirm.isBookingSuccessful(), "Booking Failed");

        String bookingId = confirm.getBookingId();

        System.out.println("Flight booked successfully. Booking ID: " + bookingId);
    }
}