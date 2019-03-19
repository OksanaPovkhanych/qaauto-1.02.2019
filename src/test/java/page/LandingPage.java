package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for Landing page.
 */
public class LandingPage  extends BasePage{

    @FindBy( xpath = "//input[@id='login-email']")
    private WebElement userEmailField;

    @FindBy ( xpath = "//input[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy( xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    @FindBy( xpath = "//*[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    /**
     * Constructor for Landing Page.
     * @param driver - WebDriver instance from BaseTest.
     */
    public LandingPage(WebDriver driver) {
       this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that does login to the site.
     * @param userEmail string representing user email.
     * @param userPassword string representing user password.
     * @param <T> type of the page.
     * @return instance of T-type page.
     */
    public  <T> T login(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
        if (driver.getCurrentUrl().equals("https://www.linkedin.com/feed/")) {
            return (T) new HomePage(driver);
        }
        if (driver.getCurrentUrl().contains("login-submit?loginSubmitSource=GUEST_HOME")) {
            return (T) new LoginSubmitPage(driver);
        }
        else {
            return (T) new LandingPage(driver);
        }
    }

    /**
     * Method that checks if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return driver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && driver.getCurrentUrl().equals("https://www.linkedin.com/")
                && (signInButton.isDisplayed());
    }

    /**
     * Method that click on forgot password link.
     * @return new instance of RequestPasswordResetPage class.
     */
    public RequestPasswordResetPage clickOnForgotPasswordLink() {
        forgotPasswordLink.click();
        return  new RequestPasswordResetPage(driver);
    }

}
