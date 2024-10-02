package shop.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import shop.AbstractComponents.AbstractComponent;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ProductCatalogPage extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".inventory_item")
    List<WebElement> products;

    @FindBy(className="title")
    WebElement pageName;

    @FindBy(css="select[data-test='product-sort-container']")
    WebElement sortingDropdown;

    Select dropdown = new Select(sortingDropdown);

    By addToCartBy = By.cssSelector(".btn_primary");
    By removeFromCartBy = By.cssSelector(".btn_secondary");
    By productPageBy = By.cssSelector(".inventory_item_name");

    public List<String> getProductNamesList() {
        /*List<String> productNames = new ArrayList<>();
        for(WebElement product : products) {
            String productName = product.findElement(productPageBy).getText();
            productNames.add(productName);
        }*/
        List<String> productNames = products.stream()
                .map(product -> product.findElement(productPageBy)
                        .getText()).collect(Collectors.toList());
        return productNames;
    }

    public WebElement getProductByName(String productName) {
        WebElement prod = products.stream().
                filter(product -> product.findElement(By.className("inventory_item_name"))
                        .getText().equals(productName))
                .findFirst().orElse(null); // we're NOT searching for that name, we're searching for a product (entire box) that has such a div with this name.
        return prod;
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
        return new ProductPage(driver);
    }

    public boolean productsWithPrice() {
        for(WebElement product : products) {
            try {
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
            catch (NoSuchElementException e) {
                return false;
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
        Collections.sort(list); // sorted copy of the original
        return list;
    }

    public List<String> reversedList(List<String> list) {
        Collections.sort(list, Collections.reverseOrder()); // reversed copy of the original

        return list;
    }

    public List<Double> getPriceList() {
        /*List<Double> priceList = new ArrayList<>();
        for(WebElement product : products) {
            String priceDollar = product.findElement(By.className("inventory_item_price")).getText();
            String priceValue = priceDollar.replace("$", "").trim();
            priceList.add(Double.parseDouble(priceValue));
        }*/
        List<Double> priceList = products.stream()
                .map(product -> product.findElement(By.className("inventory_item_price"))
                        .getText().replace("$", "").trim())
                .map(Double::parseDouble).collect(Collectors.toList());
        return priceList;
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
