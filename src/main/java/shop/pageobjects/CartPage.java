package shop.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shop.abstractcomponents.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver childDriver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.childDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".title")
    private WebElement pageName;

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = "#continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(css = ".checkout_button")
    private WebElement checkoutButton;

    By removeFromCartBy = By.cssSelector("div[class='cart_item'] button");
    By cartItemNameBy = By.cssSelector(".inventory_item_name");

    public String getPageName() {
        return pageName.getText();
    }

    public boolean getMatch(String productName) {
        //return cartItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName)); // not "filter" but "anyMatch"! anyMatch returns boolean value
        return getSingleProductByName(productName) != null;
    }

    public boolean noMatch(String productName) {
        return (!getMatch(productName));
    }

    public CheckoutAddressPage goToCheckoutAddress() {
        checkoutButton.click();
        return new CheckoutAddressPage(childDriver);
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

    public WebElement getSingleProductByName(String productName) {
        return cartItems.stream().
                filter(product -> product.findElement(cartItemNameBy)
                        .getText().equals(productName))
                .findFirst().orElse(null); // we're NOT searching for that name, we're searching for a product (entire box) that has such a div with this name.
    }

    public void removeProductFromCart(String productName) {
        WebElement prod = getSingleProductByName(productName); // searching for a specific product
        prod.findElement(removeFromCartBy).click(); // clicking on "Remove"
    }

    public List<String> getProductNamesList() {
        return cartItems.stream()
                .map(cartItem -> cartItem.findElement(cartItemNameBy).getText())
                .toList();
    }

    public ProductPage clickOnProduct(String productName) {
        WebElement prod = getSingleProductByName(productName); // searching for a specific product
        prod.findElement(cartItemNameBy).click(); // clicking on product's name to access its page
        return new ProductPage(childDriver);
    }

    public void continueShopping() {
        continueShoppingButton.click();
    }
}
