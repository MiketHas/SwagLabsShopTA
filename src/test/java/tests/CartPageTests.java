package tests;

import testcomponents.PageLauncher;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.pageobjects.CartPage;
import shop.pageobjects.CheckoutAddressPage;
import shop.pageobjects.ProductCatalogPage;
import shop.pageobjects.ProductPage;

import java.util.List;

public class CartPageTests extends PageLauncher {

    public ProductCatalogPage productCatalog;
    public CartPage cartPage;
    public String productName1 = "Sauce Labs Bike Light";
    public String productName2 = "Sauce Labs Fleece Jacket";

    @Test
    public void removeProductOnCartTest() {
        cartPage.removeProductFromCart(productName1);
        Assert.assertTrue(cartPage.noMatch(productName1));
    }

    @Test
    public void accessEachProductOnCartTest() {
        SoftAssert softAssert = new SoftAssert();
        List<String> cartItems = cartPage.getProductNamesList();
        for (String cartItem : cartItems) {
            ProductPage productPage = cartPage.clickOnProduct(cartItem);
            softAssert.assertEquals(cartItem, productPage.getProductName());
            mainMenu.goToCartPage();
        }
        softAssert.assertAll();
    }

    @Test
    public void continueCheckoutOnCartTest() {
        CheckoutAddressPage checkoutAddress = cartPage.goToCheckoutAddress();
        Assert.assertEquals(checkoutAddress.getPageName(), "Checkout: Your Information", "Can't continue from cart to Input Personal Information page!");
    }

    @Test
    public void continueShoppingOnCartTest() {
        cartPage.continueShopping();
        Assert.assertEquals(productCatalog.getPageName(), "Products", "Can't continue shopping!");
    }

    @BeforeMethod(alwaysRun = true)
    public void testSetup() {
        productCatalog = landingPage.loginApplication(getUsername(), getPassword());
        productCatalog.addProductToCart(productName1);
        productCatalog.addProductToCart(productName2);
        cartPage = mainMenu.goToCartPage();
    }
}
