package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;



/**
 * Class that contains set of account tests.
 */
public class ResetPasswordTests extends BaseTest {

    /**
     * Verify functionality of resetting password.
     * - Open new Browser
     * - Navigate to http://www.linkedin.com
     * - Verify that Landing page is loaded
     * - Click to the link 'Forgot the password?'
     * - Verify that RequestPasswordResetPage is loaded
     * - Input valid email of the user and click button "Find account"
     * - Verify that RequestPasswordResetSubmitPage is loaded
     * - Get link from the email and go by this link
     * - Verify that PasswordResetSubmitPage is loaded
     * - Input new password, confirm new password and click on submit button
     * - Verify that PasswordChangedPage is loaded
     * - Click button "Go to homepage"
     * - Verify that Home Page is loaded
     */
    @Test
    public void successfulPasswordResetTest() {
        String userEmail = "kkseniyatest@gmail.com";

        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load.");

        RequestPasswordResetPage requestPasswordResetPage = landingPage.clickOnForgotPasswordLink();
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(), "RequestPasswordResetPage did not load.");

        RequestPasswordResetSubmitPage requestPasswordResetSubmitPage = requestPasswordResetPage.findAccount(userEmail);
        Assert.assertTrue(requestPasswordResetSubmitPage.isPageLoaded(), "RequestPasswordResetSubmitPage did not load.");

        ChooseNewPasswordPage chooseNewPasswordPage = requestPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(chooseNewPasswordPage.isPageLoaded(), "ChooseNewPasswordPage did not load.");

        PasswordChangedPage passwordChangedPage = chooseNewPasswordPage.changePassword("test@2037","test@2037");
        Assert.assertTrue(passwordChangedPage.isPageLoaded(), "PasswordChangedPage did not load.");

        HomePage homePage = passwordChangedPage.goToHomepage();
        Assert.assertTrue(homePage.isPageLoaded(), "Homepage did not load.");
    }

}
