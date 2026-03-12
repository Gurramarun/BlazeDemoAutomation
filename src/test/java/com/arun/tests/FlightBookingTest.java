package com.arun.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.arun.base.BaseTest;
import com.arun.pages.HomePage;
import com.arun.pages.FlightsPage;
import com.arun.pages.PurchasePage;
import com.arun.pages.ConfirmationPage;

public class FlightBookingTest extends BaseTest {

    // Test Case 1 - Valid Booking (PASS)
    @Test
    public void bookFlightValidTest() {

        HomePage home = new HomePage(driver);
        home.selectDepartureCity("Boston");
        home.selectDestinationCity("London");
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);
        Assert.assertTrue(flights.isFlightsDisplayed(), "Flights list not displayed");

        flights.chooseFirstFlight();

        PurchasePage purchase = new PurchasePage(driver);
        purchase.enterPassengerDetails(
                "Arun",
                "Hyderabad Street",
                "Hyderabad",
                "Telangana",
                "506331"
        );

        purchase.enterPaymentDetails(
                "1234567890123456",
                "12",
                "2028",
                "Arun Gurram"
        );

        purchase.clickPurchaseFlight();

        ConfirmationPage confirm = new ConfirmationPage(driver);

        Assert.assertTrue(confirm.isBookingSuccessful(), "Booking Failed");

        String bookingId = confirm.getBookingId();
        System.out.println("Booking Successful. ID: " + bookingId);
    }


    // Test Case 2 - Invalid Departure City (FAIL)
    @Test
    public void invalidDepartureCityTest() {

        HomePage home = new HomePage(driver);

        home.selectDepartureCity("InvalidCity");
        home.selectDestinationCity("London");
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);

        Assert.assertTrue(flights.isFlightsDisplayed(), "Flights not displayed for invalid city");
    }


    // Test Case 3 - Same Departure and Destination (FAIL)
    @Test
    public void sameCityFlightTest() {

        HomePage home = new HomePage(driver);

        home.selectDepartureCity("Boston");
        home.selectDestinationCity("Boston");
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);

        Assert.assertTrue(flights.isFlightsDisplayed(), "Flights should not be shown for same cities");
    }


    // Test Case 4 - Invalid Credit Card (FAIL)
    @Test
    public void invalidPaymentDetailsTest() {

        HomePage home = new HomePage(driver);

        home.selectDepartureCity("Boston");
        home.selectDestinationCity("London");
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);
        flights.chooseFirstFlight();

        PurchasePage purchase = new PurchasePage(driver);

        purchase.enterPassengerDetails(
                "Arun",
                "Street",
                "Hyderabad",
                "Telangana",
                "500001"
        );

        purchase.enterPaymentDetails(
                "1111",      // Invalid Card
                "12",
                "2028",
                "Arun"
        );

        purchase.clickPurchaseFlight();

        ConfirmationPage confirm = new ConfirmationPage(driver);

        Assert.assertTrue(confirm.isBookingSuccessful(), "Booking should fail due to invalid card");
    }


    // Test Case 5 - Empty Passenger Name (FAIL)
    @Test
    public void emptyPassengerNameTest() {

        HomePage home = new HomePage(driver);

        home.selectDepartureCity("Boston");
        home.selectDestinationCity("London");
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);
        flights.chooseFirstFlight();

        PurchasePage purchase = new PurchasePage(driver);

        purchase.enterPassengerDetails(
                "",
                "Street",
                "Hyderabad",
                "Telangana",
                "500001"
        );

        purchase.enterPaymentDetails(
                "1234567890123456",
                "12",
                "2028",
                "Arun"
        );

        purchase.clickPurchaseFlight();

        ConfirmationPage confirm = new ConfirmationPage(driver);

        Assert.assertTrue(confirm.isBookingSuccessful(), "Booking should fail due to empty passenger name");
    }


    // Test Case 6 - Verify Booking ID Generated (PASS)
    @Test
    public void verifyBookingIdGeneratedTest() {

        HomePage home = new HomePage(driver);

        home.selectDepartureCity("Boston");
        home.selectDestinationCity("Berlin");
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);
        flights.chooseFirstFlight();

        PurchasePage purchase = new PurchasePage(driver);

        purchase.enterPassengerDetails(
                "Arun",
                "Street",
                "Hyderabad",
                "Telangana",
                "500001"
        );

        purchase.enterPaymentDetails(
                "1234567890123456",
                "12",
                "2028",
                "Arun"
        );

        purchase.clickPurchaseFlight();

        ConfirmationPage confirm = new ConfirmationPage(driver);

        String bookingId = confirm.getBookingId();

        Assert.assertNotNull(bookingId, "Booking ID not generated");

        System.out.println("Generated Booking ID: " + bookingId);
    }
}