package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for PasswordResetPage.
 */
public class PasswordResetPage {
    private WebDriver driver;

    @FindBy( xpath = "//*[@id='newPassword']")
    private WebElement newPasswordField;

    @FindBy ( xpath = "//*[@id='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy ( xpath = "//*[@id='reset-password-submit-button']")
    private WebElement submitButton;

    /**
     * Constructor for PasswordResetPage.
     * @param driver - WebDriver instance from BaseTest.
     */
    public PasswordResetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that checks if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("/checkpoint/rp/password-reset?requestSubmissionId=")
                && submitButton.isDisplayed()
                && driver.getTitle().equals("Reset Your Password | LinkedIn");
    }

    /** Method that change user password.
     * @param newPasswordText  - string representing the new password.
     * @param confirmPasswordText - string representing the confirmation of new password.
     * @return new instance of HomePage class.
     */
    public HomePage changePassword(String newPasswordText, String confirmPasswordText) {
        newPasswordField.sendKeys(newPasswordText);
        confirmPasswordField.sendKeys(confirmPasswordText);
        submitButton.click();
        return new HomePage(driver);
    }
}
