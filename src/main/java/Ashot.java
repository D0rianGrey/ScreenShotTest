import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Ashot {


    public static void main(String[] args) throws IOException {
        String url = "https://www.google.com.ua/";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);

        //Gmaillink
        WebElement gmailLink = driver.findElement(By.xpath("//a[@class='gb_g' and contains(text(),'Gmail')]"));


        //Actual
        Screenshot actualScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);

        //Actual only gmail link
        //Screenshot actualScreenshot = new AShot().takeScreenshot(driver);

        /*//ActualwithIngore
        Screenshot actualScreenshot = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .addIgnoredElement(By.xpath("//a[@class='gb_g' and contains(text(),'Gmail')]"))
                .takeScreenshot(driver);*/

        File actualFile = new File(  "testScreenshots\\actual\\" + "test" + ".png");
        ImageIO.write(actualScreenshot.getImage(), "png", actualFile);



        //Expected
        Screenshot expectedScreenshot = new Screenshot(ImageIO.read(new File("testScreenshots\\expected\\expected_5.png")));

        //Difference

        ImageDiff diff = new ImageDiffer().makeDiff(actualScreenshot,expectedScreenshot);
        diff.getDiffSize();

        //Assertion

        //.assertEquals(diff.getDiffSize(), 0);

        //Difference file

        File diffFile = new File("testScreenshots\\markedImages\\"+"difference"+".png");
        ImageIO.write(diff.getMarkedImage(), "png", diffFile);

        /*//Gif
        File[] filesArray = {actualFile, , diffFile};
        gifFile = GifSequenceWriter.createGIF(filesArray, resultGifs+name);*/


        driver.close();
    }



}
