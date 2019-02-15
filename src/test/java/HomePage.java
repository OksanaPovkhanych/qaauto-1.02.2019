import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomePage {
    WebDriver driver;
    WebElement profileNavMenuItem;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    public  void initElements(){
        profileNavMenuItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
    }

    public boolean isPageLoaded()
    {
        if (((driver.getCurrentUrl()).equals("https://www.linkedin.com/feed/"))&&(profileNavMenuItem.isDisplayed()))
        {
            return true;
        }
        else
        {
            return false;
        }
        }


}
