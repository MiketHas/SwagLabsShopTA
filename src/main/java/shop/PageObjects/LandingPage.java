package shop.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shop.AbstractComponents.AbstractComponent;

import java.util.List;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="login_credentials")
    WebElement loginCredentials;

    @FindBy(id="user-name")
    WebElement userEmail;

    @FindBy(id="password")
    WebElement userPassword;

    @FindBy(id="login-button")
    WebElement loginButton;

    @FindBy(xpath="//h3[@data-test='error']")
    WebElement errorTextElement;

    public void goTo() {
        driver.get("https://www.saucedemo.com/");
    }

    public void login(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
    }

    public ProductCatalogPage loginApplication(String email, String password) {
        login(email, password);
        return new ProductCatalogPage(driver);
    }

    public List <String> getUsernames() {
        String usernamesText = loginCredentials.getText();
        String cleanedText = usernamesText.replaceFirst("Accepted usernames are:\\n", "");
        List<String> usernames = List.of(cleanedText.split("\\n"));
        // System.out.println(usernames);
        return usernames;
    }

    public String getErrorMessage() {
        waitForElementToAppear(errorTextElement);
        String errorText = errorTextElement.getText();
        return errorText;
    }
}
