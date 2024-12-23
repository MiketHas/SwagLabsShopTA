package shop.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import shop.abstractcomponents.AbstractComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductCatalogPage extends AbstractComponent {

    WebDriver childDriver;

    public ProductCatalogPage(WebDriver driver) {
        super(driver);
        this.childDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='inventory_item']")
    private List<WebElement> products;

    @FindBy(xpath = "//*[@class='title']")
    private WebElement pageName;

    @FindBy(xpath = "//select[@data-test='product-sort-container']") // css = "select[data-test='product-sort-container']")
    private WebElement sortingDropdown;

    Select dropdown = new Select(sortingDropdown);

    By addToCartBy = By.cssSelector(".btn_primary");
    By removeFromCartBy = By.cssSelector(".btn_secondary");
    By productPageBy = By.cssSelector(".inventory_item_name");

    public List<String> getProductNamesList() {
        return products.stream()
                .map(product -> product.findElement(productPageBy)
                        .getText()).toList();
    }

    public boolean isProductPresent(String productName) {
        return getProductNamesList().contains(productName);
    }

    public WebElement getProductByName(String productName) {
        return products.stream().
                filter(product -> product.findElement(By.className("inventory_item_name"))
                        .getText().equals(productName))
                .findFirst().orElse(null); // we're NOT searching for that name, we're searching for a product (entire box) that has such a div with this name.
    }

    public void addProductToCart(String productName) {
        WebElement prod = getProductByName(productName); // searching for a specific product
        prod.findElement(addToCartBy).click(); // clicking on "Add to cart"
    }

    public void removeProductFromCart(String productName) {
        WebElement prod = getProductByName(productName); // searching for a specific product
        prod.findElement(removeFromCartBy).click(); // clicking on "Remove"
    }

    public ProductPage clickOnProduct(String productName) {
        WebElement prod = getProductByName(productName); // searching for a specific product
        prod.findElement(productPageBy).click(); // clicking on product's name to access its page
        return new ProductPage(childDriver);
    }

    public boolean productsWithPrice() {
        for (WebElement product : products) {
            String priceDollar = product.findElement(By.className("inventory_item_price")).getText();
            if (!priceDollar.startsWith("$")) {
                return false;  // Price should start with a dollar symbol
            }
            String priceValue = priceDollar.replace("$", "").trim();
            double doubleValue = Double.parseDouble(priceValue);
            if (doubleValue < 0) {
                return false; // price shouldn't be negative
            }
        }
        return true;
    }

    public String getPageName() {
        return pageName.getText();
    }

    public void sortProductsByNameAsc() {
        dropdown.selectByValue("az");
    }

    public void sortProductsByNameDesc() {
        dropdown.selectByValue("za");
    }

    public void sortProductsByPriceAsc() {
        dropdown.selectByValue("lohi");
    }

    public void sortProductsByPriceDesc() {
        dropdown.selectByValue("hilo");
    }

    public List<String> sortedList(List<String> list) {
        List<String> sortedCopy = new ArrayList<>(list);
        Collections.sort(sortedCopy); // sorted copy of the original
        return sortedCopy;
    }

    public List<String> reversedList(List<String> list) {
        List<String> reversedCopy = new ArrayList<>(list);
        Collections.sort(reversedCopy, Collections.reverseOrder()); // reversed copy of the original
        return reversedCopy;
    }

    public List<Double> getPriceList() {
        return products.stream()
                .map(product -> product.findElement(By.className("inventory_item_price"))
                        .getText().replace("$", "").trim())
                .map(Double::parseDouble).toList();
    }

    public List<Double> ascPriceList(List<Double> list) {
        List<Double> ascPriceList = new ArrayList<>(list);
        Collections.sort(ascPriceList);
        return ascPriceList;
    }

    public List<Double> descPriceList(List<Double> list) {
        List<Double> descPriceList = new ArrayList<>(list);
        Collections.sort(descPriceList, Collections.reverseOrder());
        return descPriceList;
    }


}
