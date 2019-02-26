package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LandingPage  {
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

    public  <T> T login(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
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

  /*  public  <T> T login(String userEmail, String userPassword, T pageType) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        return  pageType;
    } */

    public boolean isPageLoaded() {
        return driver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && driver.getCurrentUrl().equals("https://www.linkedin.com/")
                && (signInButton.isDisplayed());
    }

}
