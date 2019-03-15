package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Page Object class for PasswordChangedPage.
 */
public class PasswordChangedPage {
    private WebDriver driver;

    @FindBy( xpath = "//*[@id='reset-password-submit-button']")
    private WebElement goToHomepageButton;

    /**
     * Constructor for PasswordChangedPage.
     * @param driver - WebDriver instance from BaseTest.
     */
    public PasswordChangedPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that checks if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("/checkpoint/rp/password-reset-submit")
                && goToHomepageButton.isDisplayed()
                && driver.getTitle().equals("You've successfully reset your password. | LinkedIn");
    }

    /** Method that redirect to the homepage.
     * @return new instance of HomePage class.
     */
    public HomePage goToHomepage() {
        goToHomepageButton.click();
        return new HomePage(driver);
    }

}
