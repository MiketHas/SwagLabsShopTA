package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.abstractcomponents.AbstractComponent;
import shop.pageobjects.CartPage;
import shop.pageobjects.ProductCatalogPage;
import shop.pageobjects.ProductPage;
import testcomponents.PageLauncher;

import java.util.List;

public class ProductCatalogTests extends PageLauncher {

    String productName = "Sauce Labs Bike Light";
    public ProductCatalogPage productCatalog;

    @Test(dataProvider = "credentialsProvider", dataProviderClass = AbstractComponent.class)
    public void addProductToCartOnProductsPageTest(String username, String password) {
        testSetup(username, password);
        productCatalog.addProductToCart(productName);
        CartPage cartPage = mainMenu.goToCartPage();
        Assert.assertTrue(cartPage.getMatch(productName));
    }

    @Test(dataProvider = "credentialsProvider", dataProviderClass = AbstractComponent.class)
    public void removeProductFromCartOnProductsPageTest(String username, String password) {
        testSetup(username, password);
        productCatalog.addProductToCart(productName);
        productCatalog.removeProductFromCart(productName);
        CartPage cartPage = mainMenu.goToCartPage();
        Assert.assertTrue(cartPage.noMatch(productName));
    }

    @Test(dataProvider = "credentialsProvider", dataProviderClass = AbstractComponent.class)
    public void accessEachProductPageTestOnProductsPage(String username, String password) {
        testSetup(username, password);
        SoftAssert softAssert = new SoftAssert();
        List<String> products = productCatalog.getProductNamesList();
        for (String product : products) {
            ProductPage productPage = productCatalog.clickOnProduct(product);
            softAssert.assertEquals(product, productPage.getProductName());
            productPage.backToProductCatalog();
        }
        softAssert.assertAll();
    }

    @Test(dataProvider = "credentialsProvider", dataProviderClass = AbstractComponent.class)
    public void eachProductWithPriceTest(String username, String password) {
        testSetup(username, password);
        Assert.assertTrue(productCatalog.productsWithPrice(), "Not all products have price set or some of them have invalid format!");
    }

    @Test(dataProvider = "credentialsProvider", dataProviderClass = AbstractComponent.class)
    public void addProductToCartOnProductPageTest(String username, String password) {
        testSetup(username, password);
        ProductPage productPage = productCatalog.clickOnProduct(productName);
        productPage.addProductToCart();
        CartPage cartPage = mainMenu.goToCartPage();
        Assert.assertTrue(cartPage.getMatch(productName));
    }

    @Test(dataProvider = "credentialsProvider", dataProviderClass = AbstractComponent.class)
    public void removeProductFromCartOnProductPageTest(String username, String password) {
        testSetup(username, password);
        ProductPage productPage = productCatalog.clickOnProduct(productName);
        productPage.addProductToCart();
        productPage.removeProductFromCart();
        CartPage cartPage = mainMenu.goToCartPage();
        Assert.assertTrue(cartPage.noMatch(productName));
    }

    @Test(dataProvider = "credentialsProvider", dataProviderClass = AbstractComponent.class)
    public void returnToProductCatalogTest(String username, String password) {
        testSetup(username, password);
        ProductPage productPage = productCatalog.clickOnProduct(productName);
        productPage.backToProductCatalog();
        Assert.assertEquals(productCatalog.getPageName(), "Products", "Did not return to Product Catalog page!");
    }

    @Test(dataProvider = "credentialsProvider", dataProviderClass = AbstractComponent.class)
    public void sortProductsByNameAscTest(String username, String password) {
        testSetup(username, password);
        productCatalog.sortProductsByNameDesc();
        productCatalog.sortProductsByNameAsc();
        List<String> originalList = productCatalog.getProductNamesList(); // original list
        Assert.assertEquals(originalList, productCatalog.sortedList(originalList), "Products are not sorted correctly!");
    }

    @Test(dataProvider = "credentialsProvider", dataProviderClass = AbstractComponent.class)
    public void sortProductsByNameDescTest(String username, String password) {
        testSetup(username, password);
        productCatalog.sortProductsByNameDesc();
        List<String> originalList = productCatalog.getProductNamesList(); // original list
        Assert.assertEquals(originalList, productCatalog.reversedList(originalList), "Products are not sorted correctly!");
    }

    @Test(dataProvider = "credentialsProvider", dataProviderClass = AbstractComponent.class)
    public void sortProductsByPriceAscTest(String username, String password) {
        testSetup(username, password);
        productCatalog.sortProductsByPriceAsc();
        List<Double> priceList = productCatalog.getPriceList();
        Assert.assertEquals(priceList, productCatalog.ascPriceList(priceList));
    }

    @Test(dataProvider = "credentialsProvider", dataProviderClass = AbstractComponent.class)
    public void sortProductsByPriceDescTest(String username, String password) {
        testSetup(username, password);
        productCatalog.sortProductsByPriceDesc();
        List<Double> priceList = productCatalog.getPriceList();
        Assert.assertEquals(priceList, productCatalog.descPriceList(priceList));
    }

    public void testSetup(String username, String password) {
        productCatalog = landingPage.loginApplication(username, password);
    }
}
