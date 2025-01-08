package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import shop.pageobjects.CartPage;
import shop.pageobjects.CheckoutAddressPage;
import shop.pageobjects.CheckoutOverviewPage;
import shop.pageobjects.ConfirmationPage;
import shop.pageobjects.ProductCatalogPage;
import testcomponents.PageLauncher;

public class SubmitOrderTests extends PageLauncher {

    String productName = "Sauce Labs Bike Light";

    @Test
    public void submitOrderTest() {
        ProductCatalogPage productCatalog = landingPage.loginApplication(getUsername(),getPassword());
        productCatalog.addProductToCart(productName);
        CartPage cartPage = mainMenu.goToCartPage();
        CheckoutAddressPage checkoutAddress = cartPage.goToCheckoutAddress();
        checkoutAddress.enterData("Luke", "Skywalker", "00066");
        CheckoutOverviewPage checkoutSummary = checkoutAddress.goToFinish();
        ConfirmationPage confirmPage = checkoutSummary.goToConfirmation();
        Assert.assertTrue(confirmPage.orderConfirmed("Thank you for your order!"));
    }

}
