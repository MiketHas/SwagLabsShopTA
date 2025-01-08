package testcomponents;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import shop.pageobjects.LandingPage;
import shop.pageobjects.MainMenu;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PageLauncher {

    public WebDriver driver;
    public LandingPage landingPage;
    public MainMenu mainMenu;
    private static String username;
    private static String password = "secret_sauce";

    static Logger logger = Logger.getLogger(DataProvider.class.getName());

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//GlobalData.properties");
        prop.load(fis);

        String login = System.getProperty("login") != null ? System.getProperty("login") : prop.getProperty("login");
        switch (login) {
            case "Standard User" -> username = "standard_user";
            case "Locked Out User" -> username = "locked_out_user";
            case "Problem User" -> username = "problem_user";
            case "Performance Glitch User" -> username = "performance_glitch_user";
            case "Error User" -> username = "error_user";
            case "Visual User" -> username = "visual_user";
            default -> username = "standard_user";
        }
        logger.log(Level.INFO, () -> "Testing " + username);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (browserName.contains("headless")) {
                options.addArguments("--headless=old");
                //driver = new ChromeDriver(options); --------------------
            }

            // Code below is aimed to turn off Chrome's "Change your password" pop-up
            options.addArguments("--guest");
            //options.addArguments("--incognito");
            options.addArguments("--disable-web-security");
            options.addArguments("--no-proxy-server");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1920, 1080));//full screen // tak na wszelki wypadek jakby cos sie wysypywalo
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // --- Implicit/explicit !!!!!!!!!!!
        driver.manage().window().maximize();

        return driver;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        driver.manage().deleteAllCookies();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        mainMenu = new MainMenu(driver);
        landingPage.getCredentialsToExcel();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
