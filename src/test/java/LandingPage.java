import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LandingPage {
    WebDriver driver;
    WebElement emailField;
    WebElement passwordField;
    WebElement signInButton;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    public void initElements() {
        emailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        passwordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));
    }

    public void login(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();
    }

    public boolean isPageLoaded() {
        if (((driver.getTitle()).equals("LinkedIn: Log In or Sign Up"))&&(driver.getCurrentUrl()).equals("https://www.linkedin.com/")&&(signInButton.isDisplayed()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
