package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


/**
 * Page Object class for RequestPasswordResetPage.
 */
public class RequestPasswordResetPage extends  BasePage {
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
    public RequestPasswordResetSubmitPage findAccount(String userEmail) {
        userEmailField.sendKeys(userEmail);

        GMailService gMailService = new GMailService();
        gMailService.connect();

        findAccountButton.click();

        String messageSubject = "here's the link to reset your password";
        String messageTo = userEmail;
        String messageFrom = "security-noreply@linkedin.com";

        String message =  gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        int startOfLink = message.indexOf("https://www.linkedin.com/e/");

        String tempResetPasswordLink = StringUtils.substringBefore(message.substring(startOfLink), "\"");
        BasePage.resetPasswordLink = tempResetPasswordLink.replace("&amp;", "&");
        System.out.println("resetPasswordLink: " + BasePage.resetPasswordLink );

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return new RequestPasswordResetSubmitPage(driver);
    }

}
