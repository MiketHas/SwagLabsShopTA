package Tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.PageObjects.ProductCatalogPage;

public class LandingPageTests extends BaseTest {

    String correctPassword = "secret_sauce";

    @Test
    public void providedUsernamesTest() {
        SoftAssert softAssert = new SoftAssert();
        for (String userName : landingPage.getUsernames()) {
            ProductCatalogPage catalogPage = null;
            try {
                catalogPage = landingPage.loginApplication(userName, correctPassword);
                softAssert.assertEquals(catalogPage.getPageName(), "Products");
            } catch (Exception e) {
                System.err.println("Error while logging in with username: " + userName);
                softAssert.fail();
            }
            if (catalogPage != null) {
                catalogPage.openMenuButton("Logout");
            }
            driver.navigate().refresh();
        }
        softAssert.assertAll();
    }
}
