package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
     * Abstract method that checks if page is loaded.
     * @return true/false
     */
    protected abstract boolean isPageLoaded();

    protected void waitUntilElementIsClickable(WebElement webElement, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

     protected  void waitUntilElementIsClickable(WebElement webElement) {
         waitUntilElementIsClickable(webElement, 5);
     }
}
