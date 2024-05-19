import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PageBase {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @Before
    public void setUp() {
        if (driver == null) {
            initializeDriver();
        }
        configureWait();
    }

    private void initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\drivers\\chromedriver\\chromedriver.exe");

        // Add configurations
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // Initialize ChromeDriver with options
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
    }

    private void configureWait() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null; // Ensure driver is null after quitting
        }
    }
}
