package shop.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shop.abstractcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

    WebDriver childDriver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.childDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='complete-header']")
    private WebElement orderConfirmationText;

    public boolean orderConfirmed(String expectedText) {
        return orderConfirmationText.getText().equalsIgnoreCase(expectedText);
    }
}
