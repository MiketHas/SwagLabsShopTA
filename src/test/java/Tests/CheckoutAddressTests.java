package Tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import shop.PageObjects.CartPage;
import shop.PageObjects.CheckoutAddressPage;
import shop.PageObjects.CheckoutOverviewPage;
import shop.PageObjects.ProductCatalogPage;

public class CheckoutAddressTests extends BaseTest {

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

    @BeforeMethod(alwaysRun=true)
    public void login() {
        productCatalog = landingPage.loginApplication("standard_user", "secret_sauce");
        productCatalog.addProductToCart("Sauce Labs Bike Light");
        productCatalog.addProductToCart("Sauce Labs Fleece Jacket");
        cartPage = productCatalog.goToCartPage();
        checkoutPage = cartPage.goToCheckoutAddress();
    }
}
