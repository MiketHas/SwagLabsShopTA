package shop.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shop.abstractcomponents.AbstractComponent;

import java.util.List;

public class CheckoutOverviewPage extends AbstractComponent {

    WebDriver childDriver;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        this.childDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".title")
    private WebElement pageName;

    @FindBy(css = "button#finish")
    private WebElement finishButton;

    @FindBy(css = "#cancel")
    private WebElement cancelButton;

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = ".summary_subtotal_label")
    private WebElement cartValue;

    @FindBy(css = ".summary_tax_label")
    private WebElement taxValue;

    @FindBy(css = ".summary_total_label")
    private WebElement taxedCartValue;

    By cartItemNameBy = By.cssSelector(".inventory_item_name");

    public String getPageName() {
        return pageName.getText();
    }

    public ConfirmationPage goToConfirmation() {
        finishButton.click();
        return new ConfirmationPage(childDriver);
    }

    public void cancelOrder() {
        cancelButton.click();
    }

    public List<String> getProductNamesList() {
        return cartItems.stream()
                .map(cartItem -> cartItem.findElement(cartItemNameBy)
                        .getText()).toList();
    }

    public WebElement getSingleProductByName(String productName) {
        return cartItems.stream().
                filter(product -> product.findElement(cartItemNameBy)
                        .getText().equals(productName))
                .findFirst().orElse(null); // we're NOT searching for that name, we're searching for a product (entire box) that has such a div with this name.
    }

    public ProductPage clickOnProduct(String productName) {
        WebElement prod = getSingleProductByName(productName); // searching for a specific product
        prod.findElement(cartItemNameBy).click(); // clicking on product's name to access its page
        return new ProductPage(childDriver);
    }

    public Double getItemTotal() { // cart total value (excluding tax) listed on the page
        return Double.valueOf(cartValue.getText().replace("Item total: $", "").trim());
    }

    public Double calculateCartTotalValue() { // calculated sum total of every individual item in the cart
        List<Double> prices = cartItems.stream()
                .map(product -> product.findElement(By.className("inventory_item_price"))
                        .getText().replace("$", "").trim())
                .map(Double::valueOf).toList();
        return prices.stream().mapToDouble(Double::doubleValue).sum();
    }

    public Double getTax() { // tax value listed on the page
        return Double.valueOf(taxValue.getText().replace("Tax: $", "").trim());
    }

    public Double calculateTax() { // calculated 8% tax
        return Math.round((calculateCartTotalValue() * 0.08) * 100.0) / 100.0;
    }

    public Double getSum() {
        return Double.valueOf(taxedCartValue.getText().replace("Total: $", "").trim());
    }

    public Double calculateSum() { // calculated sum of cart with tax
        return Math.round((calculateCartTotalValue() + calculateTax()) * 100.0) / 100.0;
    }


}
