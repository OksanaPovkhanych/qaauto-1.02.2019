package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.*;

/**
 * Class that contains set of test of login to the site.
 */
public class LoginTests extends BaseTest {

    @DataProvider
    public Object[][] notValidData() {
        return new Object[][]{
                { "a@b.c", "" },
                { "", "a@b.c" },
                { " ", "" }
        };
    }

    /**
     * Method that checks scenario of login with not valid data, as empty email, empty password or empty both  - email and password.
     * @param userEmail string that represents user email.
     * @param userPassword string that represents user password.
     *
     * - Open new Browser
     * - Navigate to http://www.linkedin.com
     * - Verify that Landing page is loaded
     * - Login with  not valid credentials
     * - Verify that you remain on the Landing page
     */
    @Test(dataProvider = "notValidData")
    public void negativeLoginTestStaySamePageTest(String userEmail, String userPassword) {
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call.");
        landingPage.login(userEmail, userPassword);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after login with empty password.");
    }

    @DataProvider
    public Object[][] validData() {
        return new Object[][]{
                { "kkseniyatest@gmail.com", "test@1989" },
                { "kkseniyaTEST@gmail.com", "test@1989" },
                { " kkseniyatest@gmail.com", "test@1989" },
                { "kkseniyatest@gmail.com  ", "test@1989" }
        };
    }

    /**
     * Method that checks scenario of login with valid data.
     * @param userEmail string that represents user email.
     * @param userPassword string that represents user password.
     * - Open new Browser
     * - Navigate to http://www.linkedin.com
     * - Verify that Landing page is loaded
     * - Login with valid credentials
     * - Verify that Home page is loaded
     */
    @Test(dataProvider = "validData")
    public void successfulLoginTest(String userEmail, String userPassword) {
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call.");
        HomePage homePage = landingPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page did not load after login to site.");
    }

    @DataProvider
    public Object[][] notValidDataWithValidation() {
        return new Object[][]{
                { "kkseniyatest@gmail.com", "111111", "", "Hmm, that's not the right password. Please try again or request a new one." },
                { "kkseniyatestqqq@gmail.com", "test@1989","Hmm, we don't recognize that email. Please try again.","" },
                { "kkseniyatest", "test@1989", "Please enter a valid email address.", "" },
                {"343434", "111", "Be sure to include \"+\" and your country code.", ""}
        };
    }


    /**
     * Method that checks scenario of login with incorrect data as wrong email, wrong password, etc.
     * @param userEmail string that represents user email.
     * @param userPassword string that represents user password.
     * @param emailValidationMessage string that represents user email validation message.
     * @param passwordValidationMessage string that represents user password validation message.
     * - Open new Browser
     * - Navigate to http://www.linkedin.com
     * - Verify that Landing page is loaded
     * - Login with  not valid credentials
     * - Verify that LoginSubmitPage is loaded  and validation is shown
     */
    @Test(dataProvider = "notValidDataWithValidation")
    public void negativeLoginReturnToLoginSubmitTest(String userEmail,
                                                     String userPassword,
                                                     String emailValidationMessage,
                                                     String passwordValidationMessage) {
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call.");

        LoginSubmitPage loginSubmitPage = landingPage.login(userEmail, userPassword);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "page.LoginSubmitPage did not load after login to site.");
        Assert.assertEquals(loginSubmitPage.getEmailValidationMessageText(),
                emailValidationMessage,
                "Wrong validation message for email field.");
        Assert.assertEquals(loginSubmitPage.getPasswordValidationMessageText(),
                passwordValidationMessage,
                "Wrong validation message for password field.");

    }


}
