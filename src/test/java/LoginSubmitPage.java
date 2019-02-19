import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginSubmitPage {
    private WebDriver driver;
    private WebElement passwordValidationMessage;
    private WebElement emailValidationMessage;
    private WebElement loginForm;

    public LoginSubmitPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        passwordValidationMessage = driver.findElement(By.xpath("//div[@id='error-for-password']"));
        emailValidationMessage = driver.findElement(By.xpath("//div[@id='error-for-username']"));
        loginForm = driver.findElement(By.xpath("//form[@class='login__form']"));
    }

    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("login-submit?loginSubmitSource=GUEST_HOME")
                && loginForm.isDisplayed()
                && driver.getTitle().equals("Sign In to LinkedIn");
    }

    public String getPasswordValidationMessageText() {
        return passwordValidationMessage.getText();
    }

    public String getEmailValidationMessageText() {
        return emailValidationMessage.getText();
    }
}
