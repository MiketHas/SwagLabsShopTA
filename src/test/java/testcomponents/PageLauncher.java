package testcomponents;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import shop.pageobjects.LandingPage;
import shop.pageobjects.MainMenu;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PageLauncher {

    public WebDriver driver;
    public LandingPage landingPage;
    public MainMenu mainMenu;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//GlobalData.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (browserName.contains("headless")) {
                options.addArguments("--headless=old");
                driver = new ChromeDriver(options);
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

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        driver.manage().deleteAllCookies();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        mainMenu = new MainMenu(driver);
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    /*private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<LandingPage> landingPage = new ThreadLocal<>();

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//GlobalData.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        WebDriver localDriver = null;

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("start-maximized");

            localDriver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            localDriver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            localDriver = new EdgeDriver();
        }

        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.set(localDriver);
        return localDriver;
    }

    @BeforeMethod(alwaysRun = true)
    public void launchApplication() throws IOException {
        WebDriver localDriver = initializeDriver();
        LandingPage localLandingPage = new LandingPage(localDriver);
        localLandingPage.goTo();

        driver.set(localDriver);
        landingPage.set(localLandingPage);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver localDriver = driver.get();
        if (localDriver != null) {
            localDriver.quit();
            driver.remove(); // Remove driver from ThreadLocal
            landingPage.remove(); // Remove landingPage from ThreadLocal
        }

    public LandingPage getLandingPage() {
        return landingPage.get();
    }

    public WebDriver getDriver() {
        return driver.get();
    }
    }*/
}
