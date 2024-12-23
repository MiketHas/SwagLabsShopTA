package tests;

import testcomponents.PageLauncher;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import shop.pageobjects.CartPage;
import shop.pageobjects.CheckoutAddressPage;
import shop.pageobjects.CheckoutOverviewPage;
import shop.pageobjects.ProductCatalogPage;

public class CheckoutAddressTests extends PageLauncher {

    public ProductCatalogPage productCatalog;
    public CartPage cartPage;
    public CheckoutAddressPage checkoutPage;

    String firstName = "Ben";
    String secondName = "Kenobi";
    String postalCode = "62025";

    @Test
    public void continueWithPersonalInformationTest() {
        checkoutPage.enterData(firstName, secondName, postalCode);
        CheckoutOverviewPage checkoutOverviewPage = checkoutPage.goToFinish();
        Assert.assertEquals(checkoutOverviewPage.getPageName(), "Checkout: Overview", "Can't proceed with correct personal information!");
    }

    @Test
    public void cancelOrderOnAddressCheckoutTest() {
        checkoutPage.cancel();
        Assert.assertEquals(cartPage.getPageName(), "Your Cart", "Can't cancel order process!");

    }

    @BeforeMethod(alwaysRun = true)
    public void login() {
        productCatalog = landingPage.loginApplication("standard_user", "secret_sauce");
        productCatalog.addProductToCart("Sauce Labs Bike Light");
        productCatalog.addProductToCart("Sauce Labs Fleece Jacket");
        cartPage = mainMenu.goToCartPage();
        checkoutPage = cartPage.goToCheckoutAddress();
    }
}
