import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By emailField = By.xpath("//input[@id='user_name']");
    By passwordField = By.xpath("//input[@id='password']");
    By loginButton = By.xpath("//input[@class='action-button']");

    public LoginPage javascript() {
        Actions act = new Actions(driver);
        WebElement element = driver.findElement(loginButton);
        act.moveToElement(element).click().build().perform();
        return this;
    }

    public LoginPage typeUserEmail(String mail) {
        driver.findElement(emailField).sendKeys(mail);
        return this;
    }

    public LoginPage typeUserPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public void clickLogin(){
        driver.findElement(loginButton).click();
    }

}
