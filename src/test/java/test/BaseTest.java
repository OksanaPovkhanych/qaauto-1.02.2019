package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LandingPage;

/**
 *  Basic class for all classes of tests.
 */
public class BaseTest {
    private WebDriver driver;
    LandingPage landingPage;

    /**
     * Method that runs before each test, initialize driver and does some simple settings.
     *
     * - Initialize new instance of ChromeDriver
     * - Go to page https://www.linkedin.com/
     * - Initialize new instance of Landing Page
     */
    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
        landingPage = new LandingPage(driver);
    }

    /**
     * Method that runs after each test, close browser and quits driver.
     */
    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

}
