package shop.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import shop.AbstractComponents.AbstractComponent;

import java.time.Duration;

public class ProductPage extends AbstractComponent {

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="div[data-test='inventory-item-name']")
    WebElement productName;

    @FindBy(css=".btn_primary")
    WebElement addToCart;

    @FindBy(id="remove")
    WebElement removeFromCart;

    @FindBy(css="button[data-test='back-to-products']")
    WebElement backToProducts;

    public void addProductToCart() {
        addToCart.click(); // clicking on "Add to cart"
    }

    public void removeProductFromCart() {
        removeFromCart.click(); // clicking on "Remove"
    }

    public String getProductName() {
        return productName.getText();
    }

    public void backToProductCatalog() {
        backToProducts.click();
    }




}
