package shop.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import shop.PageObjects.CartPage;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".shopping_cart_link")
    WebElement cart;

    @FindBy(id="react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(id="inventory_sidebar_link")
    WebElement productPageButton;

    @FindBy(id="about_sidebar_link")
    WebElement aboutButton;

    @FindBy(id="logout_sidebar_link")
    WebElement logoutButton;

    @FindBy(id="reset_sidebar_link")
    WebElement resetButton;

    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementToDisappear(WebElement elem) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(elem));
    }

    public CartPage goToCartPage() {
        cart.click();
        return new CartPage(driver);
    }

    public void openMenuButton(String option) {
        menuButton.click();
        switch (option) {
            case "All Items" -> productPageButton.click();
            case "About" -> aboutButton.click();
            case "Logout" -> logoutButton.click();
            case "Reset" -> resetButton.click();
        }
    }
}
