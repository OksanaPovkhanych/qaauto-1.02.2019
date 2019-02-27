package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {
    private WebDriver driver;

    @FindBy( xpath = "//h3[contains(@class, 'search-results__total')]")
    private WebElement searchResultTotal;

    @FindBy ( xpath = "//*[ contains(@class,'search-results__list')]//h3[contains(@class,'search-result__title')]")
    private List<WebElement> searchResultElements;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("/search/results/all/")
                && driver.getTitle().contains("| Search | LinkedIn")
                && searchResultTotal.isDisplayed();
    }

    public int getSearchResultNumber() {
        JavascriptExecutor jsx = (JavascriptExecutor)driver;
        jsx.executeScript("window.scrollBy(0,1750)", "");
        return searchResultElements.size();
    }

    public boolean isSearchResultRelevant(String searchTerm) {
        boolean isSearchResultPresent = false;
        String searchResultElementText;
        for (WebElement searchResultElement : searchResultElements) {
            searchResultElementText = searchResultElement.getText();
            System.out.println(searchResultElementText);
            if (searchResultElementText.toLowerCase().contains(searchTerm.toLowerCase())) {
                isSearchResultPresent = true;
            } else {
                isSearchResultPresent = false;
                break;
            }
        }
        return isSearchResultPresent;
    }
}
