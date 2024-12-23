package shop.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shop.abstractcomponents.AbstractComponent;

public class CheckoutAddressPage extends AbstractComponent {

    WebDriver childDriver;

    public CheckoutAddressPage(WebDriver driver) {
        super(driver);
        this.childDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".title")
    private WebElement pageName;

    @FindBy(css = "input[name='firstName']")
    private WebElement firstNameBy;

    @FindBy(css = "input[name='lastName']")
    private WebElement lastNameBy;

    @FindBy(css = "input[name='postalCode']")
    private WebElement postalCodeBy;

    @FindBy(css = "button#cancel")
    private WebElement cancelButton;

    @FindBy(css = "input#continue")
    private WebElement continueButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorTextElement;

    public String getPageName() {
        return pageName.getText();
    }

    private void enterFirstName(String firstName) {
        firstNameBy.sendKeys(firstName);
    }

    private void enterLastName(String lastName) {
        lastNameBy.sendKeys(lastName);
    }

    private void enterPostal(String postal) {
        postalCodeBy.sendKeys(postal);
    }

    public void enterData(String first, String last, String postal) {
        enterFirstName(first);
        enterLastName(last);
        enterPostal(postal);
    }

    public CheckoutOverviewPage goToFinish() {
        continueButton.click();
        return new CheckoutOverviewPage(childDriver);
    }

    public void cancel() {
        cancelButton.click();
    }

    public String getErrorMessage() {
        return errorTextElement.getText();
    }


}
