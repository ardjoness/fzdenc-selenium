import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginPageTest.class,
        HomePageTest.class,
        SearchRecipeTest.class,
        AddRecipeTest.class,
        MultiplePageTest.class,
        ShoppingListTest.class,
        AccountTest.class,
})
public class TestSuite {
}
