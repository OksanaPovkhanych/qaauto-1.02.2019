package page;

import org.openqa.selenium.WebDriver;

/**
 * Page Object class for BasePage.
 */
public abstract class BasePage {
    protected static String resetPasswordUrl;
    protected WebDriver driver;

    /**
     * Constructor for BasePage.
     * @param driver - WebDriver instance.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method that checks if page is loaded.
     * @return true/false
     */
    public abstract boolean isPageLoaded();
}
