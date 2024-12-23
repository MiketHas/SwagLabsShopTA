package tests;

import org.testng.annotations.DataProvider;
import testcomponents.PageLauncher;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.pageobjects.ProductCatalogPage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LandingPageTests extends PageLauncher {

    String correctPassword = "secret_sauce";
    static Logger logger = Logger.getLogger(DataProvider.class.getName());


    @Test
    public void providedUsernamesTest() {
        SoftAssert softAssert = new SoftAssert();

        for (String userName : landingPage.getUsernames()) {
            ProductCatalogPage catalogPage = null;
            if (userName.equals("locked_out_user")) {
                logger.info("Omitting locked-out user from the test.");
                continue; // Skip the test for the locked-out user
            } else {
                logger.log(Level.INFO, () -> "Testing " + userName);
            }
            catalogPage = landingPage.loginApplication(userName, correctPassword);
            softAssert.assertEquals(catalogPage.getPageName(), "Products", "Wrong page accessed after login!");
            mainMenu.openMenuButton("Logout");
            driver.navigate().refresh();
        }
        softAssert.assertAll();
    }
}
