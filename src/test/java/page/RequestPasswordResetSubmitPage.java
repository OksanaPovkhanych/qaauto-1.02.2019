package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;


/**
 * Page Object class for RequestPasswordResetSubmitPage.
 */
public class RequestPasswordResetSubmitPage extends BasePage {

    @FindBy( xpath = "//*[contains(@class,'different__email') and text()='Try different email']")
    private WebElement tryDifferentAccountButton;

    /**
     * Constructor for RequestPasswordResetSubmitPage.
     * @param driver - WebDriver instance from BaseTest.
     */
    public RequestPasswordResetSubmitPage(WebDriver driver) {
        super(driver);
        // this.driver = driver;
        PageFactory.initElements(driver, this);
        }

    /**
     * Method that checks if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
       return driver.getTitle().equals("Please check your mail for reset password link. | LinkedIn")
                && driver.getCurrentUrl().contains("/checkpoint/rp/request-password-reset-submit")
                && (tryDifferentAccountButton.isDisplayed());
    }

    /** Method that go by reset password url.
     * @return new instance of ChooseNewPasswordPage.
     */
    public ChooseNewPasswordPage navigateToLinkFromEmail() {
        driver.get(resetPasswordUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return new ChooseNewPasswordPage(driver);
    }

}
