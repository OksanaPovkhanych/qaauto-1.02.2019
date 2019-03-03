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
     */
    @Test
    public void resetPasswordTest() {
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
