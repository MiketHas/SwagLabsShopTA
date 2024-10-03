package TestComponents;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import shop.PageObjects.LandingPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//GlobalData.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (browserName.contains("headless")) {
                options.addArguments("--headless=old");
                driver = new ChromeDriver(options);
            }

            // Code below is aimed to turn off Chrome's "Change your password" pop-up
            options.addArguments("--guest");
            options.addArguments("--disable-web-security");
            options.addArguments("--no-proxy-server");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            //WebDriverManager.chromedriver().setup();

            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1920,1080));//full screen // tak na wszelki wypadek jakby cos sie wysypywalo
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }

    @BeforeMethod(alwaysRun=true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() {
        driver.quit();
    }

    /*public void initializeDriver() {

        // Chrome
        // Code below is aimed to turn off Chrome's "Change your password" pop-up
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        options.addArguments("--disable-web-security");
        options.addArguments("--no-proxy-server");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // initializing driver
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Firefox
    }*/
}
