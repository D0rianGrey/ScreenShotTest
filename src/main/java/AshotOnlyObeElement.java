import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.cropper.indent.IndentCropper;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static ru.yandex.qatools.ashot.cropper.indent.IndentFilerFactory.blur;

public class AshotOnlyObeElement {
    public static void main(String[] args) throws IOException {
        String url = "https://www.google.com.ua/";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        //WebElement gmailLink = driver.findElement(By.xpath("//a[@class='gb_g' and contains(text(),'Gmail')]"));
        Screenshot actualScreenshot = new AShot().
                imageCropper(new IndentCropper().
                        addIndentFilter(blur())).
                coordsProvider(new WebDriverCoordsProvider()).
                takeScreenshot(driver, driver.findElement(By.xpath("//a[@class='gb_g' and contains(text(),'Gmail')]")));
        File actualFile = new File("testScreenshots\\actual\\" + "test" + ".png");
        ImageIO.write(actualScreenshot.getImage(), "png", actualFile);
        driver.close();

    }
}
