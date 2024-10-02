package TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
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

        String browserName = prop.getProperty("browser");
/*
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser"); // (1) Ustawiamy by mozna bylo kontrolowac zmienna "browser" z poziomu cmd
*/
        // JESLI w systemie (cmd) parametr "browser" nie jest pusty to wez stamtad browser value, jesli jest pusty to wez z getProperty
        //Przyklad: mvn test -PRegression -Dbrowser=chrome
        //String browserName = prop.getProperty("browser"); // (2) to sobie komentujemy bo juz niepotrzebne

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions(); // (1A) dodalismy to by skorzystac z opcji ChromeOptions
            /*options.addArguments("--disable-search-engine-choice-screen");*/ // <--- dodałem to tylko dlatego by sie nie pojawialo okienko wyboru wyszukiwarki Chrome od ver.127
            if (browserName.contains("headless")) {
                options.addArguments("--headless"); // (2A) dzieki temu test sie odpali BEZ odpalania przegladarki!!!!!!! (JESLI w cmd lub w Jenkins jest -chealdless)
                driver = new ChromeDriver(options); // (3A) dodalismy parametr "options" by bylo w headless mode
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

            driver = new ChromeDriver(options); // <-- dodałem parametr options: PATRZ 44
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
        driver.quit(); // THERE IS A DIFFERENCE BETWEEN driver.quit() AND driver.close() !!!!!!!!!!!!!!1
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
