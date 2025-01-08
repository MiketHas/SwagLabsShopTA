package shop.pageobjects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shop.abstractcomponents.AbstractComponent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LandingPage extends AbstractComponent {

    WebDriver childDriver;
    private static final Logger logger = Logger.getLogger(LandingPage.class.getName());

    public LandingPage(WebDriver driver) throws IOException {
        super(driver);
        this.childDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='login_credentials']")
    private WebElement loginCredentials;

    @FindBy(xpath = "//*[@class='login_password']")
    private WebElement passwordCredentials;

    @FindBy(xpath = "//*[@id='user-name']")
    private WebElement userEmail;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement userPassword;

    @FindBy(xpath = "//*[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorTextElement;

    public void goTo() {
        childDriver.get("https://www.saucedemo.com/");
    }

    public void login(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
    }

    public ProductCatalogPage loginApplication(String email, String password) {
        login(email, password);
        return new ProductCatalogPage(childDriver);
    }

    public List<String> getUsernamesFromLandingPage() {
        String usernamesText = loginCredentials.getText();
        String cleanedText = usernamesText.replaceFirst("Accepted usernames are:\\n", "");
        return List.of(cleanedText.split("\\n"));
    }

    public String getPasswordFromLandingPage() {
        String passwordText = passwordCredentials.getText();
        return passwordText.replaceFirst("Password for all users:\\n", "");
    }

    public void getCredentialsToExcel() { // this method will grab all usernames listed on the page along with a password and store it in the Excel file to be retrieved later
        // try-with-resources
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream file = new FileOutputStream("src/main/resources/Login_Credentials.xlsx")) {

            // Creating a sheet withing the file
            XSSFSheet spreadsheet = workbook.createSheet("Login data");

            // Getting current logins and current password:
            Map<String, String> credentials = new HashMap<>();
            for (int i = 0; i < getUsernamesFromLandingPage().toArray().length; i++) {
                credentials.put(getUsernamesFromLandingPage().get(i), getPasswordFromLandingPage());
            }

            Row headerRow = spreadsheet.createRow(0);
            Cell usernameHeader = headerRow.createCell(0);
            Cell passwordHeader = headerRow.createCell(1);
            usernameHeader.setCellValue("Username");
            passwordHeader.setCellValue("Password");

            int rowIndex = 1;
            for (Map.Entry<String, String> entry : credentials.entrySet()) {
                Row row = spreadsheet.createRow(rowIndex++);
                Cell usernameCell = row.createCell(0);
                usernameCell.setCellValue(entry.getKey());
                Cell passwordCell = row.createCell(1);
                passwordCell.setCellValue(entry.getValue());
            }
            workbook.write(file);
            logger.info("Excel file 'Login_Credentials' created succesfully.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Something went wrong", e);
        }
    }

    public String getErrorMessage() {
        waitForElementToAppear(errorTextElement);
        return errorTextElement.getText();
    }
}
