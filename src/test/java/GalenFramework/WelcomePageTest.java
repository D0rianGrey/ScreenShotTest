package GalenFramework;

import com.galenframework.testng.GalenTestNgTestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WelcomePageTest extends GalenTestNgTestBase {
    public WebDriver createDriver(Object[] objects) {
        return new ChromeDriver();
    }

    @Test
    public void welcomePage_shouldLookGood_onDesktopDevice() {
        load("http://google.com.ua", 1024, 768);
        //checkLayout("/specs/welcomePage.spec", asList("mobile"));
    }


}
