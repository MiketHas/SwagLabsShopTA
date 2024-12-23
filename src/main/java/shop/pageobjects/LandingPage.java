package shop.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shop.abstractcomponents.AbstractComponent;

import java.util.List;

public class LandingPage extends AbstractComponent {

    WebDriver childDriver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.childDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='login_credentials']")
    private WebElement loginCredentials;

    @FindBy(xpath = "//*[@id='user-name']")
    private WebElement userEmail;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement userPassword;

    @FindBy(xpath = "//*[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorTextElement;

    public void goTo() {
        childDriver.get("https://www.saucedemo.com/");
    }

    public void login(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
    }

    public ProductCatalogPage loginApplication(String email, String password) {
        login(email, password);
        return new ProductCatalogPage(childDriver);
    }

    public List<String> getUsernames() {
        String usernamesText = loginCredentials.getText();
        String cleanedText = usernamesText.replaceFirst("Accepted usernames are:\\n", "");
        return List.of(cleanedText.split("\\n"));
    }

    public String getErrorMessage() {
        waitForElementToAppear(errorTextElement);
        return errorTextElement.getText();
    }
}
