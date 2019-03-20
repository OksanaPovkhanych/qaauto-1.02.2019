package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for Login submit page.
 */
public class LoginSubmitPage extends BasePage{

    @FindBy( xpath = "//div[@id='error-for-password']")
    private WebElement passwordValidationMessage;

    @FindBy ( xpath = "//div[@id='error-for-username']")
    private WebElement emailValidationMessage;

    @FindBy ( xpath = "//form[@class='login__form']")
    private WebElement loginForm;

    public LoginSubmitPage(WebDriver driver) {
        super(driver);
        // this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that checks if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("login-submit?loginSubmitSource=GUEST_HOME")
                && loginForm.isDisplayed()
                && driver.getTitle().equals("Sign In to LinkedIn");
    }

    /**
     * Get a string representing the password validation message.
     * @return text of the password validation message.
     */
    public String getPasswordValidationMessageText() {
        return passwordValidationMessage.getText();
    }

    /**
     * Get a string representing the email validation message.
     * @return text of the email validation message.
     */
    public String getEmailValidationMessageText() {
        return emailValidationMessage.getText();
    }
}
