import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ashot {

    LoginPage loginPage;
    WebDriver driver;
    WebDriverWait wait;


    String url = "https://www.google.com.ua/";
    String url2 = "https://admin.vmdcs.cistest.local/";

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testMyCareer() throws InterruptedException {
        By loginButton = By.xpath("//input[@class='action-button']");
        driver.get(url2);
        loginPage = new LoginPage(driver);
        Thread.sleep(3000);
        loginPage.javascript();
        driver.findElement(By.xpath("//li/a[@class='admin-header-nav-link' and contains(text(), 'Groups')]")).click();
        driver.findElement(By.xpath("//span[@class='dx-button-text' and contains(text(), 'Expand Create Form')]")).click();
        driver.findElement(By.xpath("//input[@id='groupName']")).sendKeys("TestGroupNameAutomation1");
        driver.findElement(By.xpath("//input[@id='nameForSite']")).sendKeys("TestNameForSiteAutomation1");
        List<WebElement> webElement = driver.findElements(By.xpath("//select[@id='status']/option"));
        /*for (WebElement webElement1: webElement){
            System.out.println(webElement1.getText());
        }*/
        selectStatus("Boarding");

        driver.findElement(By.xpath("//select[@id='paymentStatus']/option[contains(text(),'Open Group')]")).click();


        driver.findElement(By.xpath("//input[@id='start_date']")).click();
        driver.findElement(By.xpath("//input[@id='start_date']")).sendKeys("12/24/1991");
        driver.findElement(By.xpath("//input[@id='start_date']")).click();







    }

    @Test
    public void testGoogle() throws IOException {
        driver.get(url);
        Screenshot actualScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        File actualFile = new File("testScreenshots\\actual\\" + "test" + ".png");
        ImageIO.write(actualScreenshot.getImage(), "png", actualFile);
        Screenshot expectedScreenshot = new Screenshot(ImageIO.read(new File("testScreenshots\\expected\\expected_5.png")));
        ImageDiff diff = new ImageDiffer().makeDiff(actualScreenshot, expectedScreenshot);
        diff.getDiffSize();
        File diffFile = new File("testScreenshots\\markedImages\\" + "difference" + ".png");
        ImageIO.write(diff.getMarkedImage(), "png", diffFile);
    }

    @AfterTest
    public void tearDown() {
        //driver.close();
    }

    public void selectStatus(String status){
        String statusXpath = String.format("//select[@id='status']/option[contains(text(),'%s')]", status);
        driver.findElement(By.xpath(statusXpath)).click();
    }

    public void selectStartDate(int data){
        String x = Integer.toString(data);
        driver.findElement(By.xpath("//input[@id='start_date']")).sendKeys(x);
    }
}
