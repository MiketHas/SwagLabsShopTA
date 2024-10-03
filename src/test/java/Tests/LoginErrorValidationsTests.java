package Tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginErrorValidationsTests extends BaseTest {

    String correctUsername = "standard_user";
    String correctPassword = "secret_sauce";
    String incorrectUsername = "user123";
    String incorrectPassword = "password";
    String errorMessage = "Epic sadface: Username and password do not match any user in this service";

    @Test
    public void noUsernameTest() {
        landingPage.login("", correctPassword);
        String errorNoUsername = "Epic sadface: Username is required";
        Assert.assertEquals(landingPage.getErrorMessage(), errorNoUsername);
    }

    @Test
    public void incorrectUsernameTest() {
        landingPage.login(incorrectUsername, correctPassword);
        Assert.assertEquals(landingPage.getErrorMessage(), errorMessage);
    }

    @Test
    public void incorrectPasswordTest() {
        landingPage.login(correctUsername, incorrectPassword);
        Assert.assertEquals(landingPage.getErrorMessage(), errorMessage);
    }

    @Test
    public void incorrectUsernameAndPasswordTest() {
        landingPage.login(incorrectUsername, incorrectPassword);
        Assert.assertEquals(landingPage.getErrorMessage(), errorMessage);
    }
}
