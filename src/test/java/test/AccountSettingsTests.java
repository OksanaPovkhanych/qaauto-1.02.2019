package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

import static java.lang.Thread.sleep;

/**
 * Class that contains set of account tests.
 */
public class AccountSettingsTests extends BaseTest {

    /**
     * Verify functionality of resetting password.
     * - Open new Browser
     * - Navigate to http://www.linkedin.com
     * - Verify that Landing page is loaded
     * - Click to the link 'Forgot the password?'
     * - Verify that RequestPasswordResetPage is loaded
     * - Input valid email of the user and click button "Find account"
     * - Verify that RequestPasswordResetSubmitPage is loaded
     * - Insert a wait with duration of 2 minutes (insert a link from the email by hands)
     * - Verify that PasswordResetSubmitPage is loaded
     * - Input new password, confirm new password and click on submit button
     * - Verify that PasswordChangedPage is loaded
     * - Click button "Go to homepage"
     * - Verify that Home Page is loaded
     */
    @Test
    public void resetPasswordTest() {
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load.");

        RequestPasswordResetPage requestPasswordResetPage = landingPage.clickOnForgotPasswordLink();
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(), "RequestPasswordResetPage did not load.");

        RequestPasswordResetSubmitPage requestPasswordResetSubmitPage = requestPasswordResetPage.findAccount("kkseniyatest@gmail.com");
        Assert.assertTrue(requestPasswordResetSubmitPage.isPageLoaded(), "RequestPasswordResetSubmitPage did not load.");

        try {
            sleep (120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        Assert.assertTrue(passwordResetPage.isPageLoaded(), "PasswordResetSubmitPage did not load.");

        PasswordChangedPage passwordChangedPage = passwordResetPage.changePassword("test@2011","test@2011");
        Assert.assertTrue(passwordChangedPage.isPageLoaded(), "PasswordChangedPage did not load.");

        HomePage homePage = passwordChangedPage.goToHomepage();
        Assert.assertTrue(homePage.isPageLoaded(), "Homepage did not load.");

    }

}
