import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    public void negativeLoginTestWithEmptyPassword() {
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call");

        landingPage.login("a@b.c", "");
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after login with empty password");
    }

    @DataProvider
    public Object[][] validData() {
        return new Object[][]{
                { "k.s.e.n.i.y.a@meta.ua", "test@1989" },
                { "K.S.E.N.I.Y.A@meta.ua", "test@1989" },
                { " k.s.e.n.i.y.a@meta.ua", "test@1989" }
        };
    }

    @Test(dataProvider = "validData")
    public void successfulLoginTest(String userEmail, String userPassword) {
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call");
        landingPage.login(userEmail, userPassword);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page did not load after login to site");
    }

    @Test
    public void negativeLoginWithWrongPassword() {
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call");

        landingPage.login("k.s.e.n.i.y.a@meta.ua", "111111");
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "LoginSubmitPage did not load after login to site");
        Assert.assertEquals(loginSubmitPage.getPasswordValidationMessageText(),
                "Hmm, that's not the right password. Please try again or request a new one.",
                "Wrong validation message for password field.");
    }

    @Test
    public void negativeLoginWithWrongEmail() {
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call");

        landingPage.login("k.s.e.n.i.y.a1@meta.ua", "test@1989");
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "LoginSubmitPage did not load after login to site");
        Assert.assertEquals(loginSubmitPage.getEmailValidationMessageText(),
                "Hmm, we don't recognize that email. Please try again.",
                "Wrong validation message for email field.");
    }

    @Test
    public void negativeLoginWithIncorrectFormatEmail() {
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call");

        landingPage.login("k.s.e.n.i.y.a", "test@1989");
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "LoginSubmitPage did not load after login to site");
        Assert.assertEquals(loginSubmitPage.getEmailValidationMessageText(),
                "Please enter a valid email address.",
                "Wrong validation message for email field.");
    }
}
