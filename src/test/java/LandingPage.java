import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    private WebDriver driver;

    @FindBy( xpath = "//input[@id='login-email']")
    private WebElement emailField;

    @FindBy ( xpath = "//input[@id='login-password']")
    private WebElement passwordField;

    @FindBy( xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage loginToHomePage(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        return new HomePage(driver);
    }

    public LoginSubmitPage loginToLoginSubmitPage(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        return new LoginSubmitPage(driver);
    }


    public LandingPage loginToLandingPage(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        return new LandingPage(driver);
    }


    public boolean isPageLoaded() {
        return driver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && driver.getCurrentUrl().equals("https://www.linkedin.com/")
                && (signInButton.isDisplayed());
    }

}
