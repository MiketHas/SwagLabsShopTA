package Tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import shop.PageObjects.*;

public class SubmitOrderErrorValidationsTests extends BaseTest {

    String correctUsername = "standard_user";
    String correctPassword = "secret_sauce";

    @Test
    public void productErrorValidationTest() { // testing if it's possible to add a non-existent product to the cart
        ProductCatalogPage productCatalog = landingPage.loginApplication(correctUsername, correctPassword);
        try {
            productCatalog.addProductToCart("Lightsaber");
            CartPage cartPage = productCatalog.goToCartPage();
            Assert.assertFalse(cartPage.getMatch("Lightsaber"), "Non-existent product was added to the cart!");
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
        CheckoutOverviewPage checkoutSummary = checkoutAddress.goToFinish();
        ConfirmationPage confirmPage = checkoutSummary.goToConfirmation();

        // Currently the shop allows for the empty order to be processed, hence the test will FAIL
        Assert.fail("Empty cart order was processed, but it should not have been.");
    }

}
