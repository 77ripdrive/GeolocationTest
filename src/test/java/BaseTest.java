import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import utils.Utils;


public class BaseTest {

    protected ChromeDriver driver;
    protected Utils utils;
    protected Actions actions;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications",
                "--disable-popup-blocking", "–disable-infobars", "--start-maximized");
        driver = new ChromeDriver(options);
        utils = new Utils();
        actions = new Actions(driver);

    }

    @AfterEach
     void tearDown() {
                driver.quit();

    }

}
