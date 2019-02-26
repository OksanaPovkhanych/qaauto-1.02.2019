package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchPage;

public class SearchTests extends BaseTest {

    @Test
    public void basicSearchTest() {
        String userEmail = "k.s.e.n.i.y.a@meta.ua";
        String userPassword = "test@1989";
        String searchTerm = "HR";
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page did not load after first call.");
        HomePage homePage = landingPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page did not load after login to site.");
        SearchPage searchPage = homePage.search(searchTerm);
        Assert.assertTrue(searchPage.isPageLoaded(), "Search page did not load.");
    }
}
