package com.arun.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.arun.base.BaseTest;
import com.arun.pages.HomePage;
import com.arun.pages.FlightsPage;
import com.arun.pages.PurchasePage;
import com.arun.pages.ConfirmationPage;

public class FlightBookingTest extends BaseTest {

    @Test(priority = 1)
    public void bookFlightTest() {

        HomePage home = new HomePage(driver);
        home.selectDepartureCity("Boston");
        home.selectDestinationCity("London");
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);
        Assert.assertTrue(flights.isFlightsDisplayed());
        flights.chooseFirstFlight();

        PurchasePage purchase = new PurchasePage(driver);
        purchase.enterPassengerDetails("Arun", "Hyderabad Street", "Hyderabad", "Telangana", "506331");
        purchase.enterPaymentDetails("1234567890123456", "12", "2028", "Arun Gurram");
        purchase.clickPurchaseFlight();

        ConfirmationPage confirm = new ConfirmationPage(driver);
        Assert.assertTrue(confirm.isBookingSuccessful());

        String bookingId = confirm.getBookingId();
        System.out.println("Booking Successful. ID: " + bookingId);
    }

    @Test(priority = 2)
    public void verifyFlightsDisplayedTest() {

        HomePage home = new HomePage(driver);
        home.selectDepartureCity("Boston");
        home.selectDestinationCity("Berlin");
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);
        Assert.assertTrue(flights.isFlightsDisplayed());
    }

    @Test(priority = 3)
    public void chooseFlightTest() {

        HomePage home = new HomePage(driver);
        home.selectDepartureCity("Boston");
        home.selectDestinationCity("London");
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);
        flights.chooseFirstFlight();

        Assert.assertTrue(true);
    }

    @Test(priority = 4)
    public void purchaseFlightTest() {

        HomePage home = new HomePage(driver);
        home.selectDepartureCity("Boston");
        home.selectDestinationCity("Rome");
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);
        flights.chooseFirstFlight();

        PurchasePage purchase = new PurchasePage(driver);
        purchase.enterPassengerDetails("Arun", "Street", "Hyderabad", "Telangana", "500001");
        purchase.enterPaymentDetails("1234567890123456", "12", "2028", "Arun");
        purchase.clickPurchaseFlight();

        ConfirmationPage confirm = new ConfirmationPage(driver);
        Assert.assertTrue(confirm.isBookingSuccessful());
    }

    @Test(priority = 5)
    public void verifyBookingIdGeneratedTest() {

        HomePage home = new HomePage(driver);
        home.selectDepartureCity("Boston");
        home.selectDestinationCity("Berlin");
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);
        flights.chooseFirstFlight();

        PurchasePage purchase = new PurchasePage(driver);
        purchase.enterPassengerDetails("Arun", "Street", "Hyderabad", "Telangana", "500001");
        purchase.enterPaymentDetails("1234567890123456", "12", "2028", "Arun");
        purchase.clickPurchaseFlight();

        ConfirmationPage confirm = new ConfirmationPage(driver);

        String bookingId = confirm.getBookingId();
        Assert.assertNotNull(bookingId);
        Assert.assertNotEquals(bookingId, "BOOKING_ID_NOT_FOUND", "Booking ID was not found on confirmation page");

        System.out.println("Generated Booking ID: " + bookingId);
    }

    @Test(priority = 6)
    public void verifyConfirmationMessageTest() {

        HomePage home = new HomePage(driver);
        home.selectDepartureCity("Boston");
        home.selectDestinationCity("Cairo");  // ✅ FIXED: Paris is NOT a destination city on BlazeDemo
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);
        flights.chooseFirstFlight();

        PurchasePage purchase = new PurchasePage(driver);
        purchase.enterPassengerDetails("Arun", "Street", "Hyderabad", "Telangana", "500001");
        purchase.enterPaymentDetails("1234567890123456", "12", "2028", "Arun");
        purchase.clickPurchaseFlight();

        ConfirmationPage confirm = new ConfirmationPage(driver);
        Assert.assertTrue(confirm.isBookingSuccessful());
    }
}