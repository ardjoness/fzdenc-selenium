import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AddRecipeTest extends PageBase{
    @Test //Send a form *5
    public void testAddRecipeWithUrl() {
        List<String> recipeUrls = List.of(
                "https://joyfoodsunshine.com/the-most-amazing-chocolate-chip-cookies/",
                "https://www.allrecipes.com/recipe/6874/best-ever-muffins/",
                "https://www.loveandlemons.com/brownies-recipe/",
                "https://www.allrecipes.com/recipe/17481/simple-white-cake/",
                "https://www.allrecipes.com/recipe/21412/tiramisu-ii/"
        );

        WebDriverWait wait = new WebDriverWait(driver, 10); // Initialize WebDriverWait
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.performLogin("seleniumtest1", "elte123"); //2*Fill input (text,radio,check...) + Send a form

        for (String recipeUrl : recipeUrls) {
            WebElement addRecipeLink = driver.findElement(By.xpath("//a[@href='/users/recipes/add' and contains(@class, 'nav-link')]")); //complex xpath (eg. //div//a[@href='asd'])
            addRecipeLink.click(); //Send a form *5

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("input-recipe-url")));

            WebElement urlField = driver.findElement(By.id("input-recipe-url"));
            urlField.sendKeys(recipeUrl); //Fill input (text,radio,check...)* 5

            WebElement saveButton = driver.findElement(By.id("btn-save-recipe-url"));
            saveButton.click(); // Send a form * 5

            wait.until(ExpectedConditions.urlContains("/users/recipes/"));

            String currentUrl = driver.getCurrentUrl();
            assert currentUrl.contains("/users/recipes/") : "Failed to navigate to the new recipe page";
        }
    }

    @Test //Send a form
    public void testAddRecipe() {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Initialize WebDriverWait
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.performLogin("seleniumtest1", "elte123"); //2*Fill input (text,radio,check...) + Send a form

        WebElement addRecipeLink = driver.findElement(By.xpath("//a[@href='/users/recipes/add' and contains(@class, 'nav-link')]"));//complex xpath (eg. //div//a[@href='asd'])
        addRecipeLink.click(); //Send a form

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputName")));

        driver.findElement(By.id("inputName")).sendKeys("Lasagne");
        driver.findElement(By.id("inputYield")).sendKeys("24");
        driver.findElement(By.id("inputPrepTime")).sendKeys("1h");
        driver.findElement(By.id("inputCookTime")).sendKeys("3 hrs and 10 mins");
        driver.findElement(By.id("inputTotalTime")).sendKeys("4h and 10 mins");
        driver.findElement(By.id("inputDescription")).sendKeys("My Lasagna Recipe");
        driver.findElement(By.id("inputIngredients")).sendKeys("For the meat sauce\n3 tbsp olive oil\n2 celery sticks, finely chopped\n1 onion, finely chopped\n1 carrot (about 100g), finely chopped\n3 garlic cloves, crushed\n140g cubetti di pancetta\n500g beef mince (we used 10% fat)\n500g pork mince or British veal mince\n200ml milk\n2 x 400g cans chopped tomatoes\n2 bay leaves\n1 rosemary sprig\n2 thyme sprigs\n2 tsp dried oregano\n2 beef stock cubes\n500ml red wine\nFor the lasagne\nabout 400g dried pasta sheets\n50g parmesan, finely grated\nFor the white sauce (béchamel)\n1.5l milk\n1 onion, thickly sliced\n3 bay leaves\n3 cloves\n100g butter\n100g plain flour\ngood grating of nutmeg");
        driver.findElement(By.id("inputInstructions")).sendKeys("Method\nSTEP 1\nFirst, make the béchamel sauce. Put the milk, onion, bay leaves and cloves into a large saucepan and bring very gently just up to the boil. Turn off the heat and set aside for 1 hr to infuse.\n\nSTEP 2\nFor the meat sauce, put the oil, celery, onion, carrot, garlic and pancetta in another large saucepan. Gently cook together until the veg is soft but not coloured.\n\nSTEP 3\nTip in the beef and pork mince, the milk and chopped tomatoes. Using a wooden spoon, stir together and break up and mash the lumps of mince against the sides of the pan. When the mince is mostly broken down, stir in all the herbs, the stock cubes and the red wine, and bring to a simmer. Cover and cook for 1 hr, stirring occasionally to stop the bottom catching.\n\nSTEP 4\nUncover and gently simmer for another 30 mins-1 hr until the meat is tender and saucy. Taste and season.\n\nSTEP 5\nTo finish the béchamel sauce, strain the milk through a fine sieve into one or two jugs. Melt the butter in the same pan then, using a wooden spoon, mix in the flour and cook for 2 mins.\n\nSTEP 6\nStir in the strained milk, a little at a time – the mix will thicken at first to a doughy paste, but keep going, adding milk gradually to avoid lumps. Bring to a gentle simmer, stirring constantly (if you have lumps, give it a quick whisk). Gently bubble for a few minutes until thickened. Season with salt, pepper and a good grating of nutmeg.\n\nSTEP 7\nHeat the oven to 180C/160C fan/gas 4. Spread a spoonful of the meat sauce over the base of a roughly 3.5-litre baking dish. Cover with a single layer of dried pasta sheets, snapping them to fit if needed, then top with a quarter of the béchamel. Spoon over a third of the meat sauce and scatter over a little of the parmesan.\n\nSTEP 8\nRepeat the layers – pasta, béchamel, meat and parmesan – two more times to use all the meat sauce. Add a final layer of pasta, the last of the béchamel and remaining parmesan. Sit the dish on a baking tray to catch spills and bake for 1 hr until bubbling, browned and crisp on top.");
        driver.findElement(By.id("inputCategories")).sendKeys("Pasta");

        WebElement saveButton = driver.findElement(By.id("btn-save-recipe-custom"));
        saveButton.click(); //9*Fill input (text,radio,check...) + Send a form

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Lasagne')]"))); //complex xpath (eg. //div//a[@href='asd'])

        WebElement recipeTitle = driver.findElement(By.xpath("//h1[contains(text(),'Lasagne')]"));//complex xpath (eg. //div//a[@href='asd'])
        assert recipeTitle.isDisplayed() : "New recipe page not displayed";
    }

    @Test //Filling textarea content
    public void fillingTextAreaContent() {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Initialize WebDriverWait
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.performLogin("seleniumtest1", "elte123"); //2*Fill input (text,radio,check...) + Send a form

        WebElement addRecipeLink = driver.findElement(By.xpath("//a[@href='/users/recipes/add' and contains(@class, 'nav-link')]")); //complex xpath (eg. //div//a[@href='asd'])
        addRecipeLink.click(); //Send a form

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputName")));

        driver.findElement(By.id("inputName")).sendKeys("Perfect Rice");
        driver.findElement(By.id("inputYield")).sendKeys("4");
        driver.findElement(By.id("inputPrepTime")).sendKeys("5 mins");
        driver.findElement(By.id("inputCookTime")).sendKeys("20 mins");
        driver.findElement(By.id("inputTotalTime")).sendKeys("25 mins");
        driver.findElement(By.id("inputDescription")).sendKeys("This rice is rich, buttery, and so comforting. It is cooked perfectly, not mushy or sticky, and has a nice savory flavor.");
        driver.findElement(By.id("inputIngredients")).sendKeys("2 tbsp butter\n1 ½ cups long grain white rice\n1 tsp kosher salt\n4 cups unsalted chicken stock or water");
        driver.findElement(By.id("inputInstructions")).sendKeys("1. Melt the butter in a saucepan.\n2. Add the rice to the melted butter and cook, stirring often, for about 1 minute.\n3. Season with kosher salt.\n4. Add the chicken stock or water.\n5. Bring to a boil, then cover the pot and simmer for about 17 minutes without stirring.\n6. Once done, fluff the rice with a fork and serve.");
        driver.findElement(By.id("inputCategories")).sendKeys("Rice");

        WebElement saveButton = driver.findElement(By.id("btn-save-recipe-custom"));
        saveButton.click(); //8*Fill input (text,radio,check...) + Send a form + Filling or reading textarea content

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Perfect Rice')]")));//complex xpath (eg. //div//a[@href='asd'])

        WebElement recipeTitle = driver.findElement(By.xpath("//h1[contains(text(),'Perfect Rice')]"));//complex xpath (eg. //div//a[@href='asd'])
        assert recipeTitle.isDisplayed() : "New recipe page not displayed";

        loginPage.performLogout();
    }
}
