package shop.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import shop.AbstractComponents.AbstractComponent;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CheckoutOverviewPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className="title")
    WebElement pageName;

    @FindBy(css="button#finish")
    WebElement finishButton;

    @FindBy(id="cancel")
    WebElement cancelButton;

    @FindBy(className="cart_item")
    List<WebElement> cartItems;

    @FindBy(css=".summary_subtotal_label")
    WebElement cartValue;

    @FindBy(css=".summary_tax_label")
    WebElement taxValue;

    @FindBy(css=".summary_total_label")
    WebElement taxedCartValue;

    By cartItemNameBy = By.cssSelector(".inventory_item_name");

    public String getPageName() {
        return pageName.getText();
    }

    public ConfirmationPage goToConfirmation() {
        finishButton.click();
        return new ConfirmationPage(driver);
    }

    public void cancelOrder() {
        cancelButton.click();
    }

    public List<String> getProductNamesList() {
        /*List<String> cartItemNames = new ArrayList<>();
        for(WebElement cartItem : cartItems) {
            String cartItemName = cartItem.findElement(cartItemNameBy).getText();
            cartItemNames.add(cartItemName);
        }*/
        List<String> cartItemNames = cartItems.stream()
                .map(cartItem -> cartItem.findElement(cartItemNameBy)
                        .getText()).collect(Collectors.toList());
        return cartItemNames;
    }

    public WebElement getSingleProductByName(String productName) {
        WebElement prod = cartItems.stream().
                filter(product -> product.findElement(cartItemNameBy)
                        .getText().equals(productName))
                .findFirst().orElse(null); // we're NOT searching for that name, we're searching for a product (entire box) that has such a div with this name.
        return prod;
    }

    public ProductPage clickOnProduct(String productName) {
        WebElement prod = getSingleProductByName(productName); // searching for a specific product
        prod.findElement(cartItemNameBy).click(); // clicking on product's name to access its page
        return new ProductPage(driver);
    }

    public Double getItemTotal() { // cart total value (excluding tax) listed on the page
        return Double.valueOf(cartValue.getText().replace("Item total: $", "").trim());
    }

    public Double calculateCartTotalValue() { // calculated sum total of every individual item in the cart
        List<Double> prices = cartItems.stream()
                .map(product -> product.findElement(By.className("inventory_item_price"))
                        .getText().replace("$", "").trim())
                .map(Double::valueOf).collect(Collectors.toList());
        Double sum = prices.stream().mapToDouble(Double::doubleValue).sum();
        return sum;
    }

    public Double getTax() { // tax value listed on the page
        return Double.valueOf(taxValue.getText().replace("Tax: $", "").trim());
    }

    public Double calculateTax() { // calculated 8% tax
        Double tax = calculateCartTotalValue()*0.08;
        Double taxRounded = Math.round(tax*100.0)/100.0;
        return taxRounded;
    }

    public Double getSum() {
        return Double.valueOf(taxedCartValue.getText().replace("Total: $", "").trim());
    }

    public Double calculateSum() { // calculated sum of cart with tax
        Double sum = calculateCartTotalValue()+calculateTax();
        Double sumRounded = Math.round(sum*100.0)/100.0;
        return sumRounded;
    }


}
