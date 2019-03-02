package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.PasswordResetPage;
import page.RequestPasswordResetPage;
import page.RequestPasswordResetSubmitPage;

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

        HomePage homePage = passwordResetPage.changePassword("test@2016","test@2016");
        Assert.assertTrue(homePage.isPageLoaded(), "HomePage did not load.");
    }

}
