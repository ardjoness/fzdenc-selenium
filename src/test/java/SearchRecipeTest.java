import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class SearchRecipeTest extends PageBase {
    @Test // Search Recipe Dropdown
    public void searchRecipeDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.performLogin("seleniumtest1", "elte123"); //2*Fill input (text,radio,check...) + Send a form

        WebElement searchRecipeLink = driver.findElement(By.cssSelector("a[href='/recipes']"));
        searchRecipeLink.click();

        WebElement submenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dropdown-menu show']"))); //complex xpath (eg. //div//a[@href='asd'])

        WebElement breakfastRecipesOption = submenu.findElement(By.xpath(".//a[contains(@href, '/recipes/breakfast-recipes')]")); //complex xpath (eg. //div//a[@href='asd'])
        breakfastRecipesOption.click(); //Send a form

        wait.until(ExpectedConditions.urlContains("/breakfast-recipes"));
        assertTrue(driver.getCurrentUrl().contains("/breakfast-recipes"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        searchRecipeLink = driver.findElement(By.cssSelector("a[href='/recipes']"));
        searchRecipeLink.click();

        submenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dropdown-menu show']"))); //complex xpath (eg. //div//a[@href='asd'])

        WebElement asianRecipesOption = submenu.findElement(By.xpath(".//a[contains(@href, '/recipes/asian-recipes')]")); //complex xpath (eg. //div//a[@href='asd'])  + Filling or reading drop-down
        asianRecipesOption.click(); //Send a form

        wait.until(ExpectedConditions.urlContains("/asian-recipes"));
        assertTrue(driver.getCurrentUrl().contains("/asian-recipes"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        searchRecipeLink = driver.findElement(By.xpath("//a[contains(@href,'/recipes')]")); //complex xpath (eg. //div//a[@href='asd'])
        searchRecipeLink.click();

        WebElement menuButton = driver.findElement(By.xpath("//li[contains(@class,'nav-item')]/a[contains(@class,'dropdown-toggle')]"));//complex xpath (eg. //div//a[@href='asd'])
        menuButton.click();

        WebElement allRecipesLink = driver.findElement(By.xpath("//a[contains(@href,'/recipes/') and contains(@class,'dropdown-item')]"));//complex xpath (eg. //div//a[@href='asd'])
        allRecipesLink.click(); //Send a form

        wait.until(ExpectedConditions.urlContains("/recipes"));

        WebElement searchInput = driver.findElement(By.xpath("//div[@id='search-recipes']//input[@name='q']")); //complex xpath (eg. //div//a[@href='asd'])
        searchInput.sendKeys("Lasagne"); //Fill input (text,radio,check...)

        WebElement searchButton = driver.findElement(By.xpath("//div[@id='search-recipes']//button")); //complex xpath (eg. //div//a[@href='asd'])
        searchButton.click(); //Send a form

        new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row row-no-top-padding']")));//complex xpath (eg. //div//a[@href='asd'])

        WebElement searchResultsContainer = driver.findElement(By.xpath("//div[@class='row row-no-top-padding']"));//complex xpath (eg. //div//a[@href='asd'])
        assertTrue("Search results are not displayed", searchResultsContainer.isDisplayed());

        List<WebElement> searchResultItems = driver.findElements(By.xpath("//div[@class='col-sm-12 col-lg-4 col-md-4']"));//complex xpath (eg. //div//a[@href='asd'])
        Assert.assertFalse("No search results found", searchResultItems.isEmpty());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // browser back navigation
        driver.navigate().back();
        wait.until(ExpectedConditions.urlContains("/asian-recipes"));
        assertTrue(driver.getCurrentUrl().contains("/asian-recipes"));


        //Test with random data
        searchRecipeLink = driver.findElement(By.xpath("//a[contains(@href,'/recipes')]")); //complex xpath (eg. //div//a[@href='asd'])
        searchRecipeLink.click();

        menuButton = driver.findElement(By.xpath("//li[contains(@class,'nav-item')]/a[contains(@class,'dropdown-toggle')]"));//complex xpath (eg. //div//a[@href='asd'])
        menuButton.click();

        allRecipesLink = driver.findElement(By.xpath("//a[contains(@href,'/recipes/') and contains(@class,'dropdown-item')]"));//complex xpath (eg. //div//a[@href='asd'])
        allRecipesLink.click(); //Send a form

        wait.until(ExpectedConditions.urlContains("/recipes"));

        searchInput = driver.findElement(By.xpath("//div[@id='search-recipes']//input[@name='q']")); //complex xpath (eg. //div//a[@href='asd'])
        searchInput.sendKeys(generateRandomString(10)); //Fill input (text,radio,check...)

        searchButton = driver.findElement(By.xpath("//div[@id='search-recipes']//button")); //complex xpath (eg. //div//a[@href='asd'])
        searchButton.click(); //Send a form


        searchResultsContainer = driver.findElement(By.xpath("//div[@class='row row-no-top-padding']"));//complex xpath (eg. //div//a[@href='asd'])
        assertTrue("Search results are not displayed", searchResultsContainer.isDisplayed());

        searchResultItems = driver.findElements(By.xpath("//div[@class='col-sm-12 col-lg-4 col-md-4']"));//complex xpath (eg. //div//a[@href='asd'])
        Assert.assertFalse("No search results found", searchResultItems.isEmpty());

        loginPage.performLogout();
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            stringBuilder.append(characters.charAt(index));
        }
        return stringBuilder.toString();
    }
}
