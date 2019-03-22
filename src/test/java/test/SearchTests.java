package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchPage;

import java.util.List;

/**
 * Class that contains set of search tests.
 */
public class SearchTests extends BaseTest {

    /**
     * Verify basic search functionality.
     *
     * - Open new Browser
     * - Navigate to http://www.linkedin.com
     * - Verify that Landing page is loaded
     * - Login with valid credentials
     * - Verify that Home page is loaded
     * - Search for "HR" search term
     * - Verify that Search page is loaded
     * - Verify that numbers of search results is 10
     * - Verify that each search result contains search term
     */
    @Test
    public void basicSearchTest() {
        String userEmail = "kkseniyatest@gmail.com";
        String userPassword = "test@2037";
        String searchTerm = "HR";
        int searchResultCount = 10;

        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call.");

        HomePage homePage = landingPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page did not load after login to site.");

        SearchPage searchPage = homePage.search(searchTerm);
        Assert.assertTrue(searchPage.isPageLoaded(), "Search page did not load.");

        Assert.assertEquals(searchPage.getSearchResultsCount(), searchResultCount, "Search result count is wrong.");
        List<String> searchResultsList = searchPage.getSearchResultsList();
        for (String searchResult: searchResultsList) {
            Assert.assertTrue(searchResult.contains(searchTerm), "Search term " + searchTerm + " not found in:\n" +searchResult);
        }
    }
}
