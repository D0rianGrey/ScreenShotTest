package AShotFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;

public class ashot_CompletePage {

    public static void main(String[] args) throws Exception {

        System.setProperty("god.bless.you", "C:\\Utility\\BrowserDrivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        WebDriver driver =  new ChromeDriver(options);
        driver.get("https://jquery.com/");
        new WebDriverWait(driver, 20).until(ExpectedConditions.titleContains("jQuery"));
        Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        ImageIO.write(myScreenshot.getImage(),"PNG",new File("./Screenshots/elementScreenshot.png"));
        driver.quit();
    }
}
