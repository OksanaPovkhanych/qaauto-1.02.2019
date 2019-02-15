import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests {
    @Test
    public void negativeLoginTestWithEmptyPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call");

        landingPage.login("a@b.c", "");
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after login with empty password");

        driver.close();
    }

    @Test
    public void successfulLoginTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call");

        landingPage.login("k.s.e.n.i.y.a@meta.ua", "test@1989");
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page did not load after login to site");

        driver.close();
    }

    @Test
    public void negativeLoginWithWrongPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call");

        landingPage.login("k.s.e.n.i.y.a@meta.ua", "111111");
        WelcomeBackPage welcomeBackPage = new WelcomeBackPage(driver);
        Assert.assertTrue(welcomeBackPage.isPageLoaded(), "Welcome back page did not load after login to site");

        driver.close();
    }

    @Test
    public void negativeLoginWithWrongEmail() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call");

        landingPage.login("k.s.e.n.i.y.a1@meta.ua", "test@1989");
        WelcomeBackPage welcomeBackPage = new WelcomeBackPage(driver);
        Assert.assertTrue(welcomeBackPage.isPageLoaded(), "Welcome back page did not load after login to site");

        driver.quit();
    }

    @Test
    public void negativeLoginWithIncorrectFormatEmail() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call");

        landingPage.login("k.s.e.n.i.y.a", "test@1989");
        WelcomeBackPage welcomeBackPage = new WelcomeBackPage(driver);
        Assert.assertTrue(welcomeBackPage.isPageLoaded(), "Welcome back page did not load after login to site");

        driver.quit();
    }
}
