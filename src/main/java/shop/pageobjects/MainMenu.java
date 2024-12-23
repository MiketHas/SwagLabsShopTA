package shop.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shop.abstractcomponents.AbstractComponent;

public class MainMenu extends AbstractComponent {

    WebDriver childDriver;

    public MainMenu(WebDriver driver) {
        super(driver);
        this.childDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='shopping_cart_link']")
    private WebElement cart;

    @FindBy(xpath = "//*[@id='react-burger-menu-btn']")
    private WebElement menuButton;

    @FindBy(xpath = "//*[@id='inventory_sidebar_link']")
    private WebElement productPageButton;

    @FindBy(xpath = "//*[@id='about_sidebar_link']")
    private WebElement aboutButton;

    @FindBy(xpath = "//*[@id='logout_sidebar_link']")
    private WebElement logoutButton;

    @FindBy(xpath = "//*[@id='reset_sidebar_link']")
    private WebElement resetButton;

    @FindBy(xpath = "//*[@class='bm-cross-button']")
    private WebElement exitMenuButton;

    public CartPage goToCartPage() {
        cart.click();
        return new CartPage(childDriver);
    }

    public void openMenuButton(String option) {
        menuButton.click();
        switch (option) {
            case "All Items" -> productPageButton.click();
            case "About" -> aboutButton.click();
            case "Logout" -> logoutButton.click();
            case "Reset" -> resetButton.click();
            default -> exitMenuButton.click();
        }
    }
}
