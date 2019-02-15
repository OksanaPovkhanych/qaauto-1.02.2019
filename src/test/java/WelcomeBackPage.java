import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WelcomeBackPage {
    private WebDriver driver;
    private WebElement signInButton;

public WelcomeBackPage(WebDriver driver) {
        this.driver = driver;
        initElements();
        }

private void initElements() {
        signInButton = driver.findElement(By.xpath("//button[@class='btn__primary--large from__button--floating']"));
        }

public boolean isPageLoaded() {
        return driver.getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
        && signInButton.isDisplayed()
        && driver.getTitle().equals("Sign In to LinkedIn");
        }
}
