package Test;

import Page.GoogleHomePage;
import Page.GoogleMapsPage;
import Page.GoogleSearchResultPage;
import Page.GoogleSearchResultPage.EntryType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class GoogleSearchTest
{

    @Test
    public void testSearchGivenKeyWord()
    {
        WebDriver driver = new ChromeDriver();
        GoogleHomePage homepage = new GoogleHomePage(driver);
        String keyword = "Chemtrails";
        homepage.goTo();
        GoogleSearchResultPage resultPage = homepage.search(keyword);
        resultPage.waitForPageToLoad(10);
        resultPage.assertIndexedEntryContainsKeyword(keyword, EntryType.ALL, 1);
        driver.quit();
    }

    @Test
    public void testSearchImage()
    {
        WebDriver driver = new ChromeDriver();
        GoogleHomePage homepage = new GoogleHomePage(driver);
        String keyword = "Dealey Plaza";
        homepage.goTo();
        GoogleSearchResultPage resultPage = homepage.search(keyword);
        resultPage.waitForPageToLoad(10);
        resultPage.selectSearchImages();
        resultPage.waitForPageToLoad(10);
        resultPage.assertIndexedEntryContainsKeyword(keyword, EntryType.IMAGES, 1);
        driver.quit();
    }


    @Test
    public void testGoogleMapsAvailableOptions()
    {
        WebDriver driver = new ChromeDriver();
        GoogleHomePage homepage = new GoogleHomePage(driver);
        homepage.goTo();
        homepage.expandGoogleAppsGrid();
        homepage.waitForPageToLoad(10);
        GoogleMapsPage googleMaps = homepage.selectGoogleMapFromAppsGrid();
        googleMaps.waitForPageToLoad(10);
        googleMaps.clickGoogleMapsMenu();
        // There is no such option called 'Flat Earth View' it has been updated to 'Globe' that toggles between
        // 'Flat Earth View' and 'Globe Earth View'
        googleMaps.assertGivenOptionsAvailable("Globe");
        googleMaps.assertGivenOptionsNotAvailable("Flat Earth View");
        driver.quit();
    }
}