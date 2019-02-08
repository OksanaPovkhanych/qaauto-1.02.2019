import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BadCodeExample {
    public static void main(String[] args) {

            System.out.println("Hello world!");
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.google.com");
            String searchTerm = "Selenium";
            WebElement searchfield =  driver.findElement(By.xpath("//input[@name='q']"));
            searchfield.sendKeys(searchTerm);
            searchfield.sendKeys(Keys.ENTER);
            List<WebElement> searchResultElements = driver.findElements(By.xpath("//div[@class='srg']/div"));
            System.out.println("Search results count: " + searchResultElements.size());

            //For each WebElement in searchResultElements print text
        for (WebElement searchResultElement : searchResultElements) {
            String searchResultElementText = searchResultElement.getText();
            System.out.println(searchResultElementText);
            if (searchResultElementText.toLowerCase().contains(searchTerm.toLowerCase()))
            {
                System.out.println("searchTerm found");
            }
            else
            {
                System.out.println("searchTerm not found");
            }
            System.out.println();
        }


            driver.quit();
    }
}
