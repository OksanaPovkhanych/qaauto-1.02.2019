package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Page Object class for Home page.
 */
public class HomePage  extends BasePage{

    @FindBy ( xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavMenuItem;

    @FindBy (xpath = "//form[@id='extended-nav-search']//input")
    private  WebElement searchField;

    /**
     * Constructor for Home page.
     * @param driver - WebDriver instance from BaseTest.
     */
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that checks if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return driver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && profileNavMenuItem.isDisplayed()
                && driver.getTitle().contains("LinkedIn");
    }

    /**
     * Method that does a search of a given text.
     * @param searchTerm string that represents text what we want to search.
     * @return new instance of SearchPage class.
     */
    public SearchPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        return new SearchPage(driver);
    }
}
