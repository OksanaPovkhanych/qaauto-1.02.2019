package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class HomePage {
    private WebDriver driver;

    @FindBy ( xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavMenuItem;

    @FindBy (xpath = "//form[@id='extended-nav-search']//input")
    private  WebElement searchField;

    public HomePage(WebDriver driver) {
         this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return driver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && profileNavMenuItem.isDisplayed()
                && driver.getTitle().contains("LinkedIn");
    }


    public SearchPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new SearchPage(driver);
    }
}
