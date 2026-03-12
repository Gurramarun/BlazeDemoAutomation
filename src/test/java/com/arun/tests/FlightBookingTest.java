package com.arun.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.arun.base.BaseTest;
import com.arun.pages.HomePage;
import com.arun.pages.FlightsPage;
import com.arun.pages.PurchasePage;
import com.arun.pages.ConfirmationPage;

public class FlightBookingTest extends BaseTest {

    // PASS
    @Test(priority = 1)
    public void bookFlightValidTest() {

        HomePage home = new HomePage(driver);
        home.selectDepartureCity("Boston");
        home.selectDestinationCity("London");
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);
        Assert.assertTrue(flights.isFlightsDisplayed());

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

        Assert.assertTrue(confirm.isBookingSuccessful());

        String bookingId = confirm.getBookingId();
        System.out.println("Booking Successful. ID: " + bookingId);
    }


    // FAIL (intentional)
    @Test(priority = 2)
    public void invalidDepartureCityTest() {

        HomePage home = new HomePage(driver);

        home.selectDepartureCity("InvalidCity");
        home.selectDestinationCity("London");
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);

        Assert.assertFalse(flights.isFlightsDisplayed());
    }


    // FAIL (intentional)
    @Test(priority = 3)
    public void sameCityFlightTest() {

        HomePage home = new HomePage(driver);

        home.selectDepartureCity("Boston");
        home.selectDestinationCity("Boston");
        home.clickFindFlights();

        FlightsPage flights = new FlightsPage(driver);

        Assert.assertFalse(flights.isFlightsDisplayed());
    }


    // PASS
    @Test(priority = 4)
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
                "1111",
                "12",
                "2028",
                "Arun"
        );

        purchase.clickPurchaseFlight();

        ConfirmationPage confirm = new ConfirmationPage(driver);

        Assert.assertTrue(confirm.isBookingSuccessful());
    }


    // PASS
    @Test(priority = 5)
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

        Assert.assertTrue(confirm.isBookingSuccessful());
    }


    // PASS
    @Test(priority = 6)
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

        Assert.assertNotNull(bookingId);

        System.out.println("Generated Booking ID: " + bookingId);
    }
}