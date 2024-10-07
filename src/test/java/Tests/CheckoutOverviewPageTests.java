package Tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.PageObjects.*;

import java.util.List;

public class CheckoutOverviewPageTests extends BaseTest {

    public ProductCatalogPage productCatalog;
    public CartPage cartPage;
    public CheckoutAddressPage checkoutPage;
    public CheckoutOverviewPage checkoutOverviewPage;
    String firstName = "Ben";
    String secondName = "Kenobi";
    String postalCode = "62025";

    @Test
    public void accessEachProductOnCheckoutTest() {
        SoftAssert softAssert = new SoftAssert();
        List<String> cartItems = checkoutOverviewPage.getProductNamesList();
        for(String cartItem : cartItems) {
            ProductPage productPage = checkoutOverviewPage.clickOnProduct(cartItem);
            softAssert.assertEquals(cartItem, productPage.getProductName());
            productPage.goToCartPage();
            checkoutPage = cartPage.goToCheckoutAddress();
            checkoutPage.enterData(firstName, secondName, postalCode);
            checkoutOverviewPage = checkoutPage.goToFinish();
        }
        softAssert.assertAll();
    }

    @Test
    public void taxCalculatedCorrectTest() {
        Assert.assertEquals(checkoutOverviewPage.getTax(), checkoutOverviewPage.calculateTax(), "Tax is not calculated correctly!");
    }

    @Test
    public void priceTotalCorrectTest() {
        Assert.assertEquals(checkoutOverviewPage.getSum(), checkoutOverviewPage.calculateSum(), "Total taxed value is not calculated properly!");
    }

    @Test
    public void cancelOrderOnCheckoutTest() {
        checkoutOverviewPage.cancelOrder();
        Assert.assertEquals(productCatalog.getPageName(), "Products", "Cancel button doesn't work properly!");
    }

    @Test
    public void finishOrderTest() {
        ConfirmationPage confirmPage = checkoutOverviewPage.goToConfirmation();
        Assert.assertTrue(confirmPage.orderConfirmed("Thank you for your order!"));
    }

    @BeforeMethod(alwaysRun=true)
    public void login() {
        productCatalog = landingPage.loginApplication("standard_user", "secret_sauce");
        productCatalog.addProductToCart("Sauce Labs Bike Light");
        productCatalog.addProductToCart("Sauce Labs Fleece Jacket");
        cartPage = productCatalog.goToCartPage();
        checkoutPage = cartPage.goToCheckoutAddress();
        checkoutPage.enterData(firstName, secondName, postalCode);
        checkoutOverviewPage = checkoutPage.goToFinish();
    }
}
