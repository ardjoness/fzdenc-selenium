import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MultiplePage extends PageBase {
    public void testUserRecipesPageContent(String pageUrl) {
        driver.get(pageUrl);

        String expectedTitle = "RecipeBox | My Recipes";
        assertEquals(expectedTitle, driver.getTitle());

        WebElement searchInput = driver.findElement(By.xpath("//input[@placeholder='Search Recipes' and @class='form-control form-control-lg']"));//complex xpath (eg. //div//a[@href='asd'])
        WebElement categoryFilter = driver.findElement(By.xpath("//select[@class='form-control form-control-lg']"));//complex xpath (eg. //div//a[@href='asd'])

        assertTrue("Search input is not displayed", searchInput.isDisplayed());
        assertTrue("Category filter is not displayed", categoryFilter.isDisplayed());

        WebElement footer = driver.findElement(By.xpath("//footer[@class='footer no-print' and @data-background-color='black']"));//complex xpath (eg. //div//a[@href='asd'])
        List<WebElement> footerLinks = footer.findElements(By.xpath(".//ul/li/a"));
        List<String> expectedFooterLinks = Arrays.asList(
                "https://www.recipebox.com/recipes", "https://www.recipebox.com/users/recipes", "https://www.recipebox.com/users/shopping", "https://www.recipebox.com/recipes/add",
                "https://www.recipebox.com/terms-and-conditions", "https://www.recipebox.com/privacy", "https://support.recipebox.com/portal/en/kb/recipebox",
                "https://support.recipebox.com/portal/en/newticket?departmentId=440698000143207371&layoutId=440698000143224658"
        );
        for (int i = 0; i < footerLinks.size(); i++) {
            assertEquals("Footer link mismatch at index " + i, expectedFooterLinks.get(i), footerLinks.get(i).getAttribute("href"));
        }

        WebElement copyright = driver.findElement(By.xpath("//footer[@class='footer no-print']//h6//span[@id='copyright']"));//complex xpath (eg. //div//a[@href='asd'])
        assertTrue("Copyright is not displayed", copyright.isDisplayed());
        String expectedLocation = "Orlando, FL";
        assertTrue("Expected location not found in page source", driver.getPageSource().contains(expectedLocation));
    }

    public void testShoppingListPageContent(String pageUrl) {
        driver.get(pageUrl);

        String expectedTitle = "RecipeBox | Grocery List";
        assertEquals(expectedTitle, driver.getTitle());

        WebElement heading = driver.findElement(By.xpath("//h1[contains(text(), 'Shopping List')]"));//complex xpath (eg. //div//a[@href='asd'])
        assertTrue(heading.isDisplayed());

        WebElement sidebarMenu = driver.findElement(By.className("sidebar-menu"));
        List<WebElement> sidebarLinks = sidebarMenu.findElements(By.tagName("a"));
        assertEquals(2, sidebarLinks.size());

        WebElement groceryList = driver.findElement(By.id("grocery-list"));
        assertTrue(groceryList.isDisplayed());

        WebElement addSectionButton = driver.findElement(By.id("section-button"));
        assertTrue(addSectionButton.isDisplayed());

        WebElement deleteAllItemsButton = driver.findElement(By.xpath("//button[contains(text(), 'Delete All Items')]"));//complex xpath (eg. //div//a[@href='asd'])
        assertTrue(deleteAllItemsButton.isDisplayed());

        WebElement footer = driver.findElement(By.tagName("footer"));
        List<WebElement> footerLinks = footer.findElements(By.tagName("a"));
        assertEquals(8, footerLinks.size());

        WebElement copyright = driver.findElement(By.id("copyright"));
        assertTrue(copyright.isDisplayed());
        String expectedLocation = "Orlando, FL";
        assertTrue(driver.getPageSource().contains(expectedLocation));
    }

    public void testAddRecipePageContent(String pageUrl) {
        driver.get(pageUrl);

        String expectedTitle = "RecipeBox | Add Recipe";
        assertEquals(expectedTitle, driver.getTitle());

        WebElement saveUrlSection = driver.findElement(By.xpath("//h2[text()='Save a URL']"));//complex xpath (eg. //div//a[@href='asd'])
        assertTrue(saveUrlSection.isDisplayed());

        WebElement createRecipeSection = driver.findElement(By.xpath("//h2[text()='Create Your Own Recipe']"));//complex xpath (eg. //div//a[@href='asd'])
        assertTrue(createRecipeSection.isDisplayed());

        WebElement recipeNameInput = driver.findElement(By.id("inputName"));
        assertTrue(recipeNameInput.isDisplayed());

        WebElement ingredientsInput = driver.findElement(By.id("inputIngredients"));
        assertTrue(ingredientsInput.isDisplayed());

        WebElement instructionsInput = driver.findElement(By.id("inputInstructions"));
        assertTrue(instructionsInput.isDisplayed());

        WebElement saveRecipeButton = driver.findElement(By.id("btn-save-recipe-custom"));
        assertTrue(saveRecipeButton.isDisplayed());
    }

    public void testAccountPageContent(String pageUrl) {
        driver.get(pageUrl);

        String expectedTitle = "RecipeBox | My Account";
        assertEquals(expectedTitle, driver.getTitle());

        WebElement usernameElement = driver.findElement(By.xpath("//p[contains(text(), 'Username: seleniumtest1')]"));//complex xpath (eg. //div//a[@href='asd'])
        WebElement emailElement = driver.findElement(By.xpath("//p[contains(text(), 'Email: abdellahabdellah9@gmail.com')]"));//complex xpath (eg. //div//a[@href='asd'])
        WebElement idElement = driver.findElement(By.xpath("//p[contains(text(), 'ID: 664a31e431b22d569e50f571')]"));//complex xpath (eg. //div//a[@href='asd'])

        assertTrue(usernameElement.isDisplayed());
        assertTrue(emailElement.isDisplayed());
        assertTrue(idElement.isDisplayed());
    }
}
