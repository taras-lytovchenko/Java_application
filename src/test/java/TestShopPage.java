import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TestShopPage {
    WebDriver driver;
    Actions actionsBuilder;

    @BeforeClass
    public void setUp(){
        System.clearProperty("webdriver.chrome.driver");
        System.setProperty("webdriver.chrome.driver","C:\\Course\\Java_application\\chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        actionsBuilder = new Actions(driver);
    }

    @Test
    public void frameTest() {
        driver.navigate().to("https://demoqa.com/frames");
        driver.switchTo().frame(3);
        driver.switchTo().frame("subframe");
        driver.findElement(By.id("sampleHeading"));

    }

    @Test
    public void verifyImageChange(){
        String dressTitle = "Красное платье в цветочный принт";
        ShopPage page = new ShopPage(driver);
        driver.navigate().to("https://sl-ir.com.ua/ua/top/");
        page.moveToItemByTitleAndHover(dressTitle);
        String thumbSource = page.clickOnThumbAndReturnSource(dressTitle, 2);
        String mainImageSource = page.getItemMainImageSource(dressTitle);

        Assert.assertEquals(mainImageSource, thumbSource);
    }
}