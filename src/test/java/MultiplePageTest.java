import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


public class MultiplePageTest extends PageBase {
    @Test
    public void testMultiplePages() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.performLogin("seleniumtest1", "elte123"); //2*Fill input (text,radio,check...) + Send a form

        MultiplePage multiplePage = new MultiplePage();
        multiplePage.driver = this.driver;
        multiplePage.wait = this.wait;

        List<String> pageUrls = Arrays.asList(
                "https://www.recipebox.com/users/recipes",
                "https://www.recipebox.com/users/shopping",
                "https://www.recipebox.com/users/recipes/add",
                "https://www.recipebox.com/users/account"
        );

        Map<String, Consumer<String>> pageTests = Map.of(
                "https://www.recipebox.com/users/recipes", multiplePage::testUserRecipesPageContent,
                "https://www.recipebox.com/users/shopping", multiplePage::testShoppingListPageContent,
                "https://www.recipebox.com/users/recipes/add", multiplePage::testAddRecipePageContent,
                "https://www.recipebox.com/users/account", multiplePage::testAccountPageContent
        );

        for (String pageUrl : pageUrls) {
            Consumer<String> testMethod = pageTests.get(pageUrl);
            if (testMethod != null) {
                testMethod.accept(pageUrl);
            } else {
                Assert.fail("No test method defined for URL: " + pageUrl);
            }
        }
    }
}
