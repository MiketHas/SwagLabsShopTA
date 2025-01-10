package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import shop.pageobjects.ProductCatalogPage;
import testcomponents.PageLauncher;

public class LandingPageTests extends PageLauncher {

    @Test
    public void canLogInTest() {
        ProductCatalogPage catalogPage = landingPage.loginApplication(getUsername(), getPassword());
        Assert.assertEquals(catalogPage.getPageName(), "Products", "Did not log in with a provided username!");
    }

    @BeforeMethod(alwaysRun = true) // Goal is just ot update the Excel file with all valid credentials from the page
    public void updateExcel() {
        landingPage.getCredentialsToExcel();
    }
}
