import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class AlertTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void test(){
        driver.get("https://demo.automationtesting.in/Alerts.html");

        driver.findElement(By.cssSelector("a[href='#Textbox']")).click();

        driver.findElement(By.cssSelector(".btn.btn-info")).click();

        Alert alert = driver.switchTo().alert();
        String nameSurname = "LadoKilasonia";
        alert.sendKeys(nameSurname);
        alert.accept();

        String ResultText = driver.findElement(By.id("demo1")).getText();
        Assert.assertTrue(ResultText.contains(nameSurname),"Result text does not contain the expected name!");
    }

    @AfterClass
    public void egarii(){
        driver.quit();
    }
}
