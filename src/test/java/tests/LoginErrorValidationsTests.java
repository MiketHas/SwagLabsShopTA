package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import testcomponents.PageLauncher;

public class LoginErrorValidationsTests extends PageLauncher {

    String incorrectUsername = "user123";
    String incorrectPassword = "password";
    String errorMessage = "Epic sadface: Username and password do not match any user in this service";

    @Test
    public void noUsernameTest() {
        landingPage.login("", getPassword());
        String errorNoUsername = "Epic sadface: Username is required";
        Assert.assertEquals(landingPage.getErrorMessage(), errorNoUsername);
    }

    @Test
    public void incorrectUsernameTest() {
        landingPage.login(incorrectUsername, getPassword());
        Assert.assertEquals(landingPage.getErrorMessage(), errorMessage);
    }

    @Test
    public void incorrectPasswordTest() {
        landingPage.login(getPassword(), incorrectPassword);
        Assert.assertEquals(landingPage.getErrorMessage(), errorMessage);
    }

    @Test
    public void incorrectUsernameAndPasswordTest() {
        landingPage.login(incorrectUsername, incorrectPassword);
        Assert.assertEquals(landingPage.getErrorMessage(), errorMessage);
    }
}
