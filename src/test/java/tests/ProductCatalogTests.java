package tests;

import testcomponents.PageLauncher;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.pageobjects.CartPage;
import shop.pageobjects.ProductCatalogPage;
import shop.pageobjects.ProductPage;

import java.util.List;

public class ProductCatalogTests extends PageLauncher {

    String productName = "Sauce Labs Bike Light";
    public ProductCatalogPage productCatalog;

    @Test
    public void addProductToCartOnProductsPageTest() {
        productCatalog.addProductToCart(productName);
        CartPage cartPage = mainMenu.goToCartPage();
        Assert.assertTrue(cartPage.getMatch(productName));
    }

    @Test
    public void removeProductFromCartOnProductsPageTest() {
        productCatalog.addProductToCart(productName);
        productCatalog.removeProductFromCart(productName);
        CartPage cartPage = mainMenu.goToCartPage();
        Assert.assertTrue(cartPage.noMatch(productName));
    }

    @Test
    public void accessEachProductPageTestOnProductsPage() {
        SoftAssert softAssert = new SoftAssert();
        List<String> products = productCatalog.getProductNamesList();
        for (String product : products) {
            ProductPage productPage = productCatalog.clickOnProduct(product);
            softAssert.assertEquals(product, productPage.getProductName());
            productPage.backToProductCatalog();
        }
        softAssert.assertAll();
    }

    @Test
    public void eachProductWithPriceTest() {
        Assert.assertTrue(productCatalog.productsWithPrice(), "Not all products have price set or some of them have invalid format!");
    }

    @Test
    public void addProductToCartOnProductPageTest() {
        ProductPage productPage = productCatalog.clickOnProduct(productName);
        productPage.addProductToCart();
        CartPage cartPage = mainMenu.goToCartPage();
        Assert.assertTrue(cartPage.getMatch(productName));
    }

    @Test
    public void removeProductFromCartOnProductPageTest() {
        ProductPage productPage = productCatalog.clickOnProduct(productName);
        productPage.addProductToCart();
        productPage.removeProductFromCart();
        CartPage cartPage = mainMenu.goToCartPage();
        Assert.assertTrue(cartPage.noMatch(productName));
    }

    @Test
    public void returnToProductCatalogTest() {
        ProductPage productPage = productCatalog.clickOnProduct(productName);
        productPage.backToProductCatalog();
        Assert.assertEquals(productCatalog.getPageName(), "Products", "Did not return to Product Catalog page!");
    }

    @Test
    public void sortProductsByNameAscTest() {
        productCatalog.sortProductsByNameDesc();
        productCatalog.sortProductsByNameAsc();
        List<String> originalList = productCatalog.getProductNamesList(); // original list
        Assert.assertEquals(originalList, productCatalog.sortedList(originalList), "Products are not sorted correctly!");
    }

    @Test
    public void sortProductsByNameDescTest() {
        productCatalog.sortProductsByNameDesc();
        List<String> originalList = productCatalog.getProductNamesList(); // original list
        Assert.assertEquals(originalList, productCatalog.reversedList(originalList), "Products are not sorted correctly!");
    }

    @Test
    public void sortProductsByPriceAscTest() {
        productCatalog.sortProductsByPriceAsc();
        List<Double> priceList = productCatalog.getPriceList();
        Assert.assertEquals(priceList, productCatalog.ascPriceList(priceList));
    }

    @Test
    public void sortProductsByPriceDescTest() {
        productCatalog.sortProductsByPriceDesc();
        List<Double> priceList = productCatalog.getPriceList();
        Assert.assertEquals(priceList, productCatalog.descPriceList(priceList));
    }

    @BeforeMethod(alwaysRun = true)
    public void login() {
        productCatalog = landingPage.loginApplication("standard_user", "secret_sauce");
    }


}
