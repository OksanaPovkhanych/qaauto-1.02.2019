package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

import java.util.concurrent.TimeUnit;

/**
 * Page Object class for RequestPasswordResetPage.
 */
public class RequestPasswordResetPage {
    private WebDriver driver;

    @FindBy( xpath = "//input[@id='username']")
    private WebElement userEmailField;

    @FindBy( xpath = "//*[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    /**
     * Constructor for RequestPasswordResetPage.
     * @param driver - WebDriver instance from BaseTest.
     */
    public RequestPasswordResetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that checks if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return driver.getTitle().equals("Reset Password | LinkedIn")
                && driver.getCurrentUrl().contains("/uas/request-password-reset?trk=uno-reg-guest-home-forgot-password")
                && (findAccountButton.isDisplayed());
    }

    /** Method that finds if account is valid.
     * @param userEmail string that representing the user email.
     * @return new instance of RequestPasswordResetSubmitPage.
     */
    public void findAccount(String userEmail) {
        userEmailField.sendKeys(userEmail);
        String messageSubject = "here's the link to reset your password";
        String messageTo = userEmail;
        String messageFrom = "security-noreply@linkedin.com";

        GMailService gMailService = new GMailService();
        gMailService.connect();

        findAccountButton.click();
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

       // return new RequestPasswordResetSubmitPage(driver);
    }

}
