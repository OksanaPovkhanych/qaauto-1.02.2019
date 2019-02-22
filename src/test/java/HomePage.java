import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage  {
    private WebDriver driver;

    @FindBy ( xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavMenuItem;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return driver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && profileNavMenuItem.isDisplayed()
                && driver.getTitle().contains("LinkedIn");
    }


}
