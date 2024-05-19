import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.*;

public class HomePageTest extends PageBase {

    @Test //static page test
    public void HomeTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.performLogin("seleniumtest1", "elte123"); //2*Fill input (text,radio,check...) + Send a form

        WebElement logo = driver.findElement(By.xpath("//a[@class='navbar-brand']")); //complex xpath (eg. //div//a[@href='asd'])
        logo.click(); //Send a form

        String expectedTitle = "RecipeBox | Store your favorite recipes in one place for free";
        assertEquals(expectedTitle, driver.getTitle());

        WebElement heading = driver.findElement(By.xpath("//h1[contains(text(), 'Store, organize, and shop your favorite recipes for free.')]")); //complex xpath (eg. //div//a[@href='asd'])
        assertTrue(heading.isDisplayed());

        WebElement joinButton = driver.findElement(By.xpath("//div[@class='container']//a[contains(@href, '/register') and contains(text(), 'Join Free')]"));//complex xpath (eg. //div//a[@href='asd'])
        assertTrue(joinButton.isDisplayed());

        List<WebElement> navLinks = driver.findElements(By.xpath("//ul[@class='navbar-nav']//a")); //complex xpath (eg. //div//a[@href='asd'])
        int visibleNavLinksCount = (int) navLinks.stream().filter(WebElement::isDisplayed).count();
        assertEquals(6, visibleNavLinksCount);

        List<WebElement> testimonials = driver.findElements(By.xpath("//div[@id='section-testimonials']//blockquote")); //complex xpath (eg. //div//a[@href='asd'])
        assertEquals(3, testimonials.size());
        for (WebElement testimonial : testimonials) {
            assertTrue(testimonial.isDisplayed());
            WebElement quote = testimonial.findElement(By.xpath(".//p"));
            assertNotNull(quote.getText());
            WebElement author = testimonial.findElement(By.xpath(".//p[@class='blockquote-footer']")); //complex xpath (eg. //div//a[@href='asd'])
            assertNotNull(author.getText());
        }

        List<WebElement> footerLinks = driver.findElements(By.xpath("//footer//a"));
        assertEquals(8, footerLinks.size());
        for (WebElement link : footerLinks) {
            assertTrue(link.isDisplayed());
            assertNotNull(link.getAttribute("href"));
        }

        //Scroll to the bottom of the page
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");


        //Highlight an logo
        WebElement element = driver.findElement(By.xpath("//a[@class='navbar-brand']"));
        jsExecutor.executeScript("arguments[0].style.border='3px solid red'", element);


        loginPage.performLogout();
    }
}
