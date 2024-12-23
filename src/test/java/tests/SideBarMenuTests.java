package tests;

import testcomponents.PageLauncher;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import shop.pageobjects.CartPage;
import shop.pageobjects.ProductCatalogPage;

public class SideBarMenuTests extends PageLauncher {

    String productName = "Sauce Labs Bike Light";
    public ProductCatalogPage productCatalog;

    @Test
    public void allItemsButtonTest() {
        productCatalog.addProductToCart(productName);
        mainMenu.goToCartPage();
        mainMenu.openMenuButton("All Items");
        Assert.assertEquals(productCatalog.getPageName(), "Products", "Did not return to Product Catalog page!");
    }

    @Test
    public void aboutButtonTest() {
        productCatalog.addProductToCart(productName);
        mainMenu.goToCartPage();
        mainMenu.openMenuButton("About");
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://saucelabs.com/");
    }

    @Test
    public void logoutButtonTest() {
        productCatalog.addProductToCart(productName);
        mainMenu.goToCartPage();
        mainMenu.openMenuButton("Logout");
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.saucedemo.com/");
    }

    @Test
    public void resetPageTest() {
        productCatalog.addProductToCart(productName);
        mainMenu.openMenuButton("Reset");
        CartPage cartPage = mainMenu.goToCartPage();
        Assert.assertTrue(cartPage.isCartEmpty());
    }

    @BeforeMethod(alwaysRun = true)
    public void login() {
        productCatalog = landingPage.loginApplication("standard_user", "secret_sauce");
    }
}
