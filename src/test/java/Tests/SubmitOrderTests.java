package Tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import shop.PageObjects.*;
import shop.PageObjects.CartPage;

public class SubmitOrderTests extends BaseTest {

    String productName = "Sauce Labs Bike Light";

    @Test
    public void submitOrderTest() {
        ProductCatalogPage productCatalog = landingPage.loginApplication("standard_user", "secret_sauce");
        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();
        CheckoutAddressPage checkoutAddress = cartPage.goToCheckoutAddress();
        checkoutAddress.enterData("Luke", "Skywalker", "00066");
        CheckoutOverviewPage checkoutSummary = checkoutAddress.goToFinish();
        ConfirmationPage confirmPage = checkoutSummary.goToConfirmation();
        Assert.assertTrue(confirmPage.orderConfirmed("Thank you for your order!"));
    }

}
