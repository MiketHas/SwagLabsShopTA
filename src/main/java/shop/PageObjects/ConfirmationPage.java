package shop.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shop.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className="complete-header")
    WebElement orderConfirmationText;

    public boolean orderConfirmed(String expectedText) {
        return orderConfirmationText.getText().equalsIgnoreCase(expectedText);
    }
}
