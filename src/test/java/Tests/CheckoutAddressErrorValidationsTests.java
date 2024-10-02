package Tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import shop.PageObjects.CartPage;
import shop.PageObjects.CheckoutAddressPage;
import shop.PageObjects.CheckoutOverviewPage;
import shop.PageObjects.ProductCatalogPage;

public class CheckoutAddressErrorValidationsTests extends BaseTest {
    public ProductCatalogPage productCatalog;
    public CartPage cartPage;
    public CheckoutAddressPage checkoutPage;

    String firstName = "Ben";
    String secondName = "Kenobi";
    String postalCode = "62025";

    @Test
    public void continueWithNoPersonalInformationTest() {
        CheckoutOverviewPage checkoutOverviewPage = checkoutPage.goToFinish();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required", "It's possible to login without personal information!");
    }

    @Test
    public void continueWithoutFirstNameTest() {
        checkoutPage.enterData("", secondName, postalCode);
        CheckoutOverviewPage checkoutOverviewPage = checkoutPage.goToFinish();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required", "It's possible to login without first name!");
    }

    @Test
    public void continueWithoutSecondNameTest() {
        checkoutPage.enterData(firstName, "", postalCode);
        CheckoutOverviewPage checkoutOverviewPage = checkoutPage.goToFinish();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required", "It's possible to login without second name!");
    }

    @Test
    public void continueWithoutPostalTest() {
        checkoutPage.enterData(firstName, secondName, "");
        CheckoutOverviewPage checkoutOverviewPage = checkoutPage.goToFinish();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required", "It's possible to login without postal code!");
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
