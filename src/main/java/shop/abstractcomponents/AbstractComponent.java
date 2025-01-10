package shop.abstractcomponents;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

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

    public static Object[][] getCredentialsFromExcel() throws IOException { // method no longer necessary as logins have been parameterized. Method left here as a proof that it can be done.
        String filePath = "src/main/resources/Login_Credentials.xlsx";
        FileInputStream file = new FileInputStream(filePath);

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<Object[]> credentials = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            String username = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();
            credentials.add(new Object[]{username, password});
        }

        workbook.close();
        return credentials.toArray(new Object[0][0]);
    }
}
