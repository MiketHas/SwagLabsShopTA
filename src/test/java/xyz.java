import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class xyz {

    public static void main(String[] args) throws InterruptedException {
        // Setup WebDriver (Uncomment the WebDriverManager line if needed)
        // WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Open the SauceDemo website
        driver.get("https://www.saucedemo.com/");

        // Log in
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Use WebDriverWait to ensure elements are loaded before interacting with them
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the product list is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item")));

        // Get all product elements
        List<WebElement> products = driver.findElements(By.className("inventory_item"));

        // Check if the list is populated
        if (products.isEmpty()) {
            System.out.println("No products found!");
        } else {
            System.out.println("Number of products found: " + products.size());

            // Log all product names for debugging purposes
            for (WebElement product : products) {
                String productName = product.findElement(By.className("inventory_item_name")).getText();
                System.out.println("Product found: " + productName);
            }

            // Search for the product "Sauce Labs Bike Light"
            WebElement prod = products.stream()
                    .filter(product -> product.findElement(By.className("inventory_item_name"))
                            .getText().equals("Sauce Labs Bike Light"))
                    .findFirst()
                    .orElse(null);

            // Check if the product was found
            if (prod != null) {
                // Add the product to the cart
                prod.findElement(By.cssSelector(".pricebar button")).click();
                System.out.println("Product added to cart.");
            } else {
                System.out.println("Product 'Sauce Labs Bike Light' not found.");
            }
        }

        // Optional: Sleep for observation purposes
        Thread.sleep(5000);

        // Quit the browser
        driver.quit();
    }
}
