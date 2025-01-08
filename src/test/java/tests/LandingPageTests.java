package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import shop.pageobjects.ProductCatalogPage;
import testcomponents.PageLauncher;

public class LandingPageTests extends PageLauncher {

    @Test
    public void canLogInTest() {
        ProductCatalogPage catalogPage = landingPage.loginApplication(getUsername(), getPassword());
        Assert.assertEquals(catalogPage.getPageName(), "Products", "Did not log in with a provided username!");
    }
}
