import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingListTest extends PageBase{

    @Test
    public void testGroceryListPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.performLogin("seleniumtest1", "elte123"); //2*Fill input (text,radio,check...) + Send a form

        WebElement searchRecipeLink = driver.findElement(By.cssSelector("a[href='/users/shopping']"));
        searchRecipeLink.click();
        String expectedTitle = "RecipeBox | Grocery List";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement heading = driver.findElement(By.xpath("//h1[contains(text(), 'Shopping List')]"));
        Assert.assertTrue(heading.isDisplayed());

        WebElement addSectionButton = driver.findElement(By.id("section-button"));
        Assert.assertTrue(addSectionButton.isDisplayed());

        WebElement groceryItemInput = driver.findElement(By.xpath("//input[@placeholder='Add grocery item']"));
        Assert.assertTrue(groceryItemInput.isDisplayed());

        WebElement deleteAllItemsButton = driver.findElement(By.xpath("//button[contains(text(), 'Delete All Items')]"));
        Assert.assertTrue(deleteAllItemsButton.isDisplayed());

        addSectionButton = driver.findElement(By.id("section-button"));
        addSectionButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='grocerySectionModal']//input[@type='text']")));
        WebElement sectionNameInput = driver.findElement(By.xpath("//div[@id='grocerySectionModal']//input[@type='text']"));
        sectionNameInput.sendKeys("dairy products");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement saveSectionButton = driver.findElement(By.xpath("//div[@id='grocerySectionModal']//button[contains(text(), 'Save Section')]"));
        saveSectionButton.click();

        WebElement newSectionAnchor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/h3/a[contains(@href, 'section-')]")));
        Assert.assertTrue(newSectionAnchor.isDisplayed());

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        groceryItemInput = driver.findElement(By.xpath("//input[@placeholder='Add grocery item']"));
        groceryItemInput.sendKeys("cheese");
        WebElement formControl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@class='form-control']")));
        formControl.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Select select = new Select(formControl);
        select.selectByIndex(3);

        WebElement saveItemButton = driver.findElement(By.xpath("//button[@class='btn btn-primary' and contains(text(), 'Save item')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", saveItemButton);

        loginPage.performLogout();
    }
}
