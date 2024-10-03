package shop.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shop.AbstractComponents.AbstractComponent;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className="title")
    WebElement pageName;

    @FindBy(className="cart_item")
    List<WebElement> cartItems;

    @FindBy(id="continue-shopping")
    WebElement continueShoppingButton;

    @FindBy(css=".checkout_button")
    WebElement checkoutButton;

    By removeFromCartBy = By.cssSelector("div[class='cart_item'] button");
    By cartItemNameBy = By.cssSelector(".inventory_item_name");

    public String getPageName() {
        return pageName.getText();
    }

    public boolean getMatch(String productName) {
        //return cartItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName)); // not "filter" but "anyMatch"! anyMatch returns boolean value
        return getSingleProductByName(productName)!=null;
    }

    public boolean noMatch(String productName) {
        return (!getMatch(productName));
    }

    public CheckoutAddressPage goToCheckoutAddress() {
        checkoutButton.click();
        return new CheckoutAddressPage(driver);
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

    public WebElement getSingleProductByName(String productName) {
        WebElement prod = cartItems.stream().
                filter(product -> product.findElement(cartItemNameBy)
                        .getText().equals(productName))
                .findFirst().orElse(null); // we're NOT searching for that name, we're searching for a product (entire box) that has such a div with this name.
        return prod;
    }

    public void removeProductFromCart(String productName) {
        WebElement prod = getSingleProductByName(productName); // searching for a specific product
        prod.findElement(removeFromCartBy).click(); // clicking on "Remove"
    }

    public List<String> getProductNamesList() {
        /*List<String> cartItemNames = new ArrayList<>();
        for(WebElement cartItem : cartItems) {
            String cartItemName = cartItem.findElement(cartItemNameBy).getText();
            cartItemNames.add(cartItemName);
        }*/
        List<String> cartItemNames = cartItems.stream()
                .map(cartItem -> cartItem.findElement(cartItemNameBy).getText())
                .collect(Collectors.toList());
        return cartItemNames;
    }

    public ProductPage clickOnProduct(String productName) {
        WebElement prod = getSingleProductByName(productName); // searching for a specific product
        prod.findElement(cartItemNameBy).click(); // clicking on product's name to access its page
        return new ProductPage(driver);
    }

    public void continueShopping() {
        continueShoppingButton.click();
    }
}
