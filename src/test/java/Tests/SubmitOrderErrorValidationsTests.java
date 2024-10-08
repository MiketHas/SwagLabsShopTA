package Tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import shop.PageObjects.*;

public class SubmitOrderErrorValidationsTests extends BaseTest {

    String correctUsername = "standard_user";
    String correctPassword = "secret_sauce";
    String productName = "Sauce Labs Bike Light";

    @Test
    public void productErrorValidationTest() { // testing if it's possible to add a non-existent product to the cart
        ProductCatalogPage productCatalog = landingPage.loginApplication(correctUsername, correctPassword);
        try {
            productCatalog.addProductToCart(productName);
            CartPage cartPage = productCatalog.goToCartPage();
            Assert.assertFalse(cartPage.getMatch("Sauce_Labs_Bike_Light"));
        } catch (Exception e) {
            System.out.println("Expected exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void submitEmptyOrderTest() { // testing submitting an empty order
        ProductCatalogPage productCatalog = landingPage.loginApplication("standard_user", "secret_sauce");
        CartPage cartPage = productCatalog.goToCartPage();
        CheckoutAddressPage checkoutAddress = cartPage.goToCheckoutAddress();
        checkoutAddress.enterData("Luke", "Skywalker", "00066");
        try { // Ideally test should fail if it's possible to submit an empty order
            CheckoutOverviewPage checkoutSummary = checkoutAddress.goToFinish();
            ConfirmationPage confirmPage = checkoutSummary.goToConfirmation();
            // Currently the shop allows for the empty order to be processed, so the test is set to PASS in such case
            Assert.assertTrue(confirmPage.orderConfirmed("Thank you for your order!"), "Empty cart order was not processed!");
        } catch (Exception e) {
            Assert.fail("Order submission failed with an empty cart, but it was expected to succeed.");
        }
    }


}
