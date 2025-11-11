import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class FormTests {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @Test
    public void test() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        try {
            WebElement adIframe = driver.findElement(By.cssSelector("iframe[id^='google_ads']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none'", adIframe);
        } catch (NoSuchElementException ignored) {}
        String Name = "Lado";
        String Surname = "Kilasonia";
        String Email = "Lado.kilasonia2005@gmail.com";
        String Number = "5555555555";
        String BirthMonth = "October";
        String BirthYear = "2005";
        String Subject1="Maths";
        String Subject2="English";
        String Hobby1="Sports";
        String Hobby3= "Music";
        String Address="Georgia,Tbilisi";
        String State = "Haryana";
        String City = "Karnal";
        driver.findElement(By.id("firstName")).sendKeys(Name);
        driver.findElement(By.id("lastName")).sendKeys(Surname);
        driver.findElement(By.id("userEmail")).sendKeys(Email);

        driver.findElement(By.cssSelector("label[for='gender-radio-1']")).click();
        driver.findElement(By.id("userNumber")).sendKeys(Number);

        WebElement dobInput  = driver.findElement(By.id("dateOfBirthInput"));

        dobInput.click();
        new Select(driver.findElement(By.className("react-datepicker__month-select"))).selectByValue("9");

        new Select(driver.findElement(By.className("react-datepicker__year-select"))).selectByValue("2005");
        driver.findElement(By.className("react-datepicker__day--010")).click();



        WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));
        subjectsInput.sendKeys(Subject1);
        subjectsInput.sendKeys(Keys.ENTER);
        subjectsInput.sendKeys(Subject2);
        subjectsInput.sendKeys(Keys.ENTER);

        Scrolldown();
        WebElement hobbies3 = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-3']"));
        WebElement hobbies1 = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']"));


        driver.findElement(By.id("currentAddress")).sendKeys(Address);

        WebElement stateDropdown = driver.findElement(By.id("state"));

        stateDropdown.click();


        driver.findElement(By.id("react-select-3-option-2")).click();


        Thread.sleep(3000);
        WebElement cityDropdown = driver.findElement(By.id("city"));
        cityDropdown.click();

        driver.findElement(By.id("react-select-4-option-0")).click();



        WebElement submitBtn = driver.findElement(By.id("submit"));
        submitBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));

        WebElement modalContent = driver.findElement(By.className("table-responsive"));
        String popupText = modalContent.getText();



        Assert.assertTrue(popupText.contains(Name));
        Assert.assertTrue(popupText.contains(Surname));
        Assert.assertTrue(popupText.contains(Email));
        Assert.assertTrue(popupText.contains(Number));

        Assert.assertTrue(popupText.contains(BirthYear));
        Assert.assertTrue(popupText.contains(Subject1));
        Assert.assertTrue(popupText.contains(Subject2));
        Assert.assertTrue(popupText.contains(Address));
        Assert.assertTrue(popupText.contains(State));
        Assert.assertTrue(popupText.contains(City));

    }




    private void Scrolldown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)","");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
