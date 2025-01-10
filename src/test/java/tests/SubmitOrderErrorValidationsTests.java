package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import shop.pageobjects.CartPage;
import shop.pageobjects.CheckoutAddressPage;
import shop.pageobjects.CheckoutOverviewPage;
import shop.pageobjects.ProductCatalogPage;
import testcomponents.PageLauncher;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SubmitOrderErrorValidationsTests extends PageLauncher {

    static Logger logger = Logger.getLogger(SubmitOrderErrorValidationsTests.class.getName());

    String fakeProduct = "Lightsaber";

    @Test
    public void productErrorValidationTest() { // testing if it's possible to add a non-existent product to the cart
        ProductCatalogPage productCatalog = landingPage.loginApplication(getUsername(), getPassword());
        if (!productCatalog.isProductPresent(fakeProduct)) {
            logger.log(Level.INFO, () -> "It's not possible to buy " + fakeProduct + ".");
        } else {
            productCatalog.addProductToCart(fakeProduct);
            CartPage cartPage = mainMenu.goToCartPage();
            Assert.assertFalse(cartPage.getMatch(fakeProduct), "Non-existent product was added to the cart!");
        }
    }

    @Test
    public void submitEmptyOrderTest() { // testing submitting an empty order
        landingPage.loginApplication(getUsername(), getPassword());
        CartPage cartPage = mainMenu.goToCartPage();
        CheckoutAddressPage checkoutAddress = cartPage.goToCheckoutAddress();
        checkoutAddress.enterData("Luke", "Skywalker", "00066");
        CheckoutOverviewPage checkoutSummary = checkoutAddress.goToFinish();
        checkoutSummary.goToConfirmation();

        // Currently the shop allows for the empty order to be processed, hence the test will FAIL
        Assert.fail("Empty cart order was processed, but it should not have been.");
    }

}
