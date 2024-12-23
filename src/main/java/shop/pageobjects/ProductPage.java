package shop.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shop.abstractcomponents.AbstractComponent;

public class ProductPage extends AbstractComponent {

    WebDriver childDriver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.childDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@data-test='inventory-item-name']")
    private WebElement productName;

    @FindBy(xpath = "//button[contains(@class,'btn_primary')]")
    private WebElement addToCart;

    @FindBy(xpath = "//button[contains(@id,'remove')]")
    private WebElement removeFromCart;

    @FindBy(xpath = "//button[@data-test='back-to-products']")
    private WebElement backToProducts;

    /*@FindBy(css = ".btn_primary")
    private WebElement addToCart;

    @FindBy(id = "remove")
    private WebElement removeFromCart;

    @FindBy(css = "button[data-test='back-to-products']")
    private WebElement backToProducts;*/

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
