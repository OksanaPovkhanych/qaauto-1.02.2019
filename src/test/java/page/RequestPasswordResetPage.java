package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

import java.util.concurrent.TimeUnit;


/**
 * Page Object class for RequestPasswordResetPage.
 */
public class RequestPasswordResetPage extends  BasePage {

    @FindBy( xpath = "//input[@id='username']")
    private WebElement userEmailField;

    @FindBy( xpath = "//*[@id='reset-password-submit-button']")
    private WebElement findAccountButton;


    /**
     * Constructor for RequestPasswordResetPage.
     * @param driver - WebDriver instance from BaseTest.
     */
    public RequestPasswordResetPage(WebDriver driver) {
        super(driver);
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


    /** Method that finds if account is valid and also method get ResetPasswordUrl.
     * @param userEmail string that representing the user email.
     * @return new instance of RequestPasswordResetSubmitPage.
     */
    public RequestPasswordResetSubmitPage findAccount(String userEmail) {
        userEmailField.sendKeys(userEmail);

        GMailService gMailService = new GMailService();
        gMailService.connect();

        findAccountButton.click();

        String messageSubject = "here's the link to reset your password";
        String messageTo = userEmail;
        String messageFrom = "security-noreply@linkedin.com";

        String message =  gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);

        int startPosition = message.indexOf("https://www.linkedin.com/e/");
        String tempResetPasswordUrl = StringUtils.substringBefore(message.substring(startPosition),
                "\" style=\"cursor:pointer;color:#008CC9;-webkit-text-size-adjust:100%;display:inline-block;text-decoration:none;-ms-text-size-adjust:100%;\">Reset my password");
        resetPasswordUrl = tempResetPasswordUrl.replace("amp;", "");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return new RequestPasswordResetSubmitPage(driver);
    }

}
