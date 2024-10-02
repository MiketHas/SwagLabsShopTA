package shop.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shop.AbstractComponents.AbstractComponent;

public class CheckoutAddressPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutAddressPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className="title")
    WebElement pageName;

    @FindBy(css="input[name='firstName']")
    WebElement firstNameBy;

    @FindBy(css="input[name='lastName']")
    WebElement lastNameBy;

    @FindBy(css="input[name='postalCode']")
    WebElement postalCodeBy;

    @FindBy(css="button#cancel")
    WebElement cancelButton;

    @FindBy(css="input#continue")
    WebElement continueButton;

    @FindBy(xpath="//h3[@data-test='error']")
    WebElement errorTextElement;

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
        return new CheckoutOverviewPage(driver);
    }

    public void cancel() {
        cancelButton.click();
    }

    public String getErrorMessage() {
        return errorTextElement.getText();
    }


}
