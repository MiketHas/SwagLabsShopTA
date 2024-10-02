package Tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import shop.PageObjects.CartPage;
import shop.PageObjects.ProductCatalogPage;

public class SideBarMenuTests extends BaseTest {

    String productName = "Sauce Labs Bike Light";
    public ProductCatalogPage productCatalog;

    @Test
    public void allItemsButtonTest() {
        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();
        cartPage.openMenuButton("All Items");
        Assert.assertEquals(productCatalog.getPageName(), "Products", "Did not return to Product Catalog page!");
    }

    @Test
    public void aboutButtonTest() {
        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();
        cartPage.openMenuButton("About");
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://saucelabs.com/" );
    }

    @Test
    public void logoutButtonTest() {
        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();
        cartPage.openMenuButton("Logout");
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://www.saucedemo.com/" );
    }

    @Test
    public void resetPageTest() {
        productCatalog.addProductToCart(productName);
        productCatalog.openMenuButton("Reset");
        CartPage cartPage = productCatalog.goToCartPage();
        Assert.assertTrue(cartPage.isCartEmpty());
    }

    @BeforeMethod(alwaysRun=true)
    public void login() {
        productCatalog = landingPage.loginApplication("standard_user", "secret_sauce");
    }
}
