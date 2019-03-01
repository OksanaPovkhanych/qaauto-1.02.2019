package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SearchPage {
    private WebDriver driver;

    @FindBy( xpath = "//h3[contains(@class, 'search-results__total')]")
    private WebElement searchResultTotal;

    @FindBy ( xpath = "//li[contains(@class, 'search-result ')]")
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

    public int getSearchResultsCount() {
     return searchResultElements.size();
    }

    public boolean isSearchResultRelevant(String searchTerm) {
        boolean isSearchResultPresent = false;
        String searchResultElementText;
        for (WebElement searchResultElement : searchResultElements) {
            searchResultElementText = searchResultElement.getText();
            if (searchResultElementText.toLowerCase().contains(searchTerm.toLowerCase())) {
                isSearchResultPresent = true;
            } else {
                isSearchResultPresent = false;
                break;
            }
        }
        return isSearchResultPresent;
    }

    public List<String> getSearchResultsList() {
        List<String> searchResultStringList = new ArrayList<String>();
        for (WebElement searchResultElement: searchResultElements) {
            searchResultStringList.add(searchResultElement.getText());
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", searchResultElement);
        }
        return searchResultStringList;
    }

}
