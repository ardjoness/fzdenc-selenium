import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Method to get page title
    public String getPageTitle() {
        return driver.getTitle();
    }

    private void waitForElementToBeVisible(By locator) { //explicit wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void verifyCurrentPage(By locator, String errorMessage) {
        waitForElementToBeVisible(locator);
        assertTrue(errorMessage, driver.findElement(locator).isDisplayed());
    }

    void performLogin(String username, String password) {
        openLoginPage();
        verifyLoginPageDisplayed();
        fillAndSubmitLoginFormWithCredentials(username, password);
    }

    void openLoginPage() {
        driver.get("https://www.recipebox.com/users/login");
    }

    private void verifyLoginPageDisplayed() {
        verifyCurrentPage(By.xpath("//div[@class='card-header text-center']/h3[contains(text(),'Sign In')]"), "Not on the login page"); //complex xpath (eg. //div//a[@href='asd'])
    }

    private void fillAndSubmitLoginFormWithCredentials(String username, String password) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click(); //complex xpath (eg. //div//a[@href='asd'])
    }

    void verifyLoginSuccess() {
        verifyCurrentPage(By.xpath("//div[@id='my-recipes']//h2[contains(text(),\"Let's get cooking!\")]"), "Login was not successful"); //complex xpath (eg. //div//a[@href='asd'])
    }

    void performLogout() {
        clickLogoutButton();
        verifyLogoutSuccess();
    }

    private void clickLogoutButton() {
        driver.findElement(By.xpath("//a[@href='/users/logout']")).click(); //complex xpath (eg. //div//a[@href='asd'])
    }

    private void verifyLogoutSuccess() {
        verifyCurrentPage(By.xpath("//div[@class='card-header text-center']/h3[contains(text(),'Sign In')]"), "Logout was not successful"); //complex xpath (eg. //div//a[@href='asd'])
    }

    String getErrorMessageText() {
        return driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText(); //complex xpath (eg. //div//a[@href='asd'])
    }
}
