import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountTest extends PageBase{
    @Test //Send a form
    public void testAddRecipe() {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Initialize WebDriverWait
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.performLogin("seleniumtest1", "elte123"); //2*Fill input (text,radio,check...) + Send a form

        WebElement accountLink = driver.findElement(By.xpath("//a[@href='/users/account' and contains(@class, 'nav-link')]"));//complex xpath (eg. //div//a[@href='asd'])
        accountLink.click(); //Send a form

        String pageTitle = driver.getTitle();
        assert pageTitle.equals("RecipeBox | My Account") : "Page title assertion failed";


        WebElement usernameElement = driver.findElement(By.xpath("//p[contains(text(),'Username')]")); //complex xpath (eg. //div//a[@href='asd'])
        assert usernameElement.isDisplayed() : "Username assertion failed";
        assert usernameElement.getText().contains("seleniumtest1") : "Username value assertion failed";

        WebElement emailElement = driver.findElement(By.xpath("//p[contains(text(),'Email')]"));//complex xpath (eg. //div//a[@href='asd'])
        assert emailElement.isDisplayed() : "Email assertion failed";
        assert emailElement.getText().contains("abdellahabdellah9@gmail.com") : "Email value assertion failed";

        WebElement idElement = driver.findElement(By.xpath("//p[contains(text(),'ID')]"));//complex xpath (eg. //div//a[@href='asd'])
        assert idElement.isDisplayed() : "ID assertion failed";
        assert idElement.getText().contains("664a31e431b22d569e50f571") : "ID value assertion failed";

        loginPage.performLogout();
    }
}
