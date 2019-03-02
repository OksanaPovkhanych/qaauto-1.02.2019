package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Page Object class for SearchPage.
 */
public class SearchPage {
    private WebDriver driver;

    @FindBy( xpath = "//h3[contains(@class, 'search-results__total')]")
    private WebElement searchResultTotal;

    @FindBy ( xpath = "//li[contains(@class, 'search-result ')]")
    private List<WebElement> searchResultElements;

    /**
     * Constructor for SearchPage.
     * @param driver - WebDriver instance from BaseTest.
     */
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that checks if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("/search/results/all/")
                && driver.getTitle().contains("| Search | LinkedIn")
                && searchResultTotal.isDisplayed();
    }

    /**
     * Method that get count of search results on the page.
     * @return count of search results.
     */
    public int getSearchResultsCount() {
     return searchResultElements.size();
    }

    /** Method that get text of each search result element on the page.
     * @return list of text of each search result element on the page.
     */
    public List<String> getSearchResultsList() {
        List<String> searchResultStringList = new ArrayList<String>();
        for (WebElement searchResultElement: searchResultElements) {
            searchResultStringList.add(searchResultElement.getText());
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", searchResultElement);
        }
        return searchResultStringList;
    }

}
