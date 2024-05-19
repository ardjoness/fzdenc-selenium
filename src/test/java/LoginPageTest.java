import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPageTest extends PageBase {

    @Test
    public void testLoginPageTitle() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.openLoginPage();
        String actualTitle = loginPage.getPageTitle();
        String expectedTitle = "RecipeBox | Sign In";
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testValidLoginAndLogout() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.performLogin("seleniumtest1", "elte123"); //Fill simple form and send (eg. Login)
        loginPage.verifyLoginSuccess();
        loginPage.performLogout(); //Logout
    }

    @Test
    public void testInvalidLoginInvalidUsername() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.performLogin("invaliduser", "elte123"); //2*Fill input (text,radio,check...) + Form sending with user
        String actualErrorText = loginPage.getErrorMessageText();
        assertTrue("Expected error message to contain 'No user: invaliduser', but was: " + actualErrorText,
                actualErrorText.contains("No user: invaliduser"));
    }

    @Test
    public void testInvalidLoginInvalidPassword() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.performLogin("seleniumtest1", "wrongpassword"); //2*Fill input (text,radio,check...) + Send a form
        String actualErrorText = loginPage.getErrorMessageText();
        assertTrue("Expected error message to contain 'Invalid credentials', but was: " + actualErrorText,
                actualErrorText.contains("Invalid credentials"));
    }
}
