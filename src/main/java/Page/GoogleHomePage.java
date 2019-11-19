package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GoogleHomePage implements SearchEngine
{
    private WebDriver _driver;
    protected final String SEARCH_INPUT_FIELD = "//input[@class='gLFyf gsfi']";
    protected final String GOOGLE_HOMEPAGE_URL = "https://www.google.com/";
    protected final String GOOGLE_APPS_GRID = "//a[@title='Google apps']";
    protected final String GOOGLE_APPS = "(//ul[@jsname='k77Iif']/li)";
    protected final String GOOGLE_APPS_ACCOUNT = "(//ul[@jsname='k77Iif']/li)[1]";
    protected final String GOOGLE_APPS_SEARCH = "(//ul[@jsname='k77Iif']/li)[2]";
    protected final String GOOGLE_APPS_MAPS = "(//ul[@jsname='k77Iif']/li)[3]";
    protected final String GOOGLE_APPS_YOUTUBE = "(//ul[@jsname='k77Iif']/li)[4]";
    protected final String GOOGLE_APPS_PLAY = "(//ul[@jsname='k77Iif']/li)[5]";
    protected final String GOOGLE_APPS_GMAIL = "(//ul[@jsname='k77Iif']/li)[6]";
    protected final String GOOGLE_APPS_CONTACTS = "(//ul[@jsname='k77Iif']/li)[7]";
    protected final String GOOGLE_APPS_DRIVE = "(//ul[@jsname='k77Iif']/li)[8]";
    protected final String GOOGLE_APPS_CALENDAR = "(//ul[@jsname='k77Iif']/li)[9]";
    protected final String GOOGLE_APPS_TRANSLATOR = "(//ul[@jsname='k77Iif']/li)[10]";
    protected final String GOOGLE_APPS_PHOTOS = "(//ul[@jsname='k77Iif']/li)[11]";
    protected final String GOOGLE_APPS_SHOPPING = "(//ul[@jsname='k77Iif']/li)[12]";

    public GoogleHomePage (WebDriver driver)
    {
        this._driver = driver;
    }

    protected static void sleep (int sec)
    {
        try
        {
            Thread.sleep(sec * 1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void waitForPageToLoad(int timeOutSeconds)
    {
        ExpectedCondition<Boolean> expectation =
                new ExpectedCondition<Boolean>()
                {
                    public Boolean apply(WebDriver driver)
                    {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try
        {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(getWebDriver(), timeOutSeconds);
            wait.until(expectation);
        } catch (Throwable error)
        {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    public WebDriver getWebDriver ()
    {
        return _driver;
    }

    public GoogleSearchResultPage search (String searchTerm)
    {
        _driver.findElement(By.xpath(SEARCH_INPUT_FIELD)).sendKeys(searchTerm);
        _driver.findElement(By.xpath(SEARCH_INPUT_FIELD)).sendKeys(Keys.ENTER);
        return new GoogleSearchResultPage(_driver);
    }

    public void goTo ()
    {
        _driver.get(GOOGLE_HOMEPAGE_URL);
    }

    public void expandGoogleAppsGrid ()
    {
        _driver.findElement(By.xpath(GOOGLE_APPS_GRID)).click();
    }

    public GoogleMapsPage selectGoogleMapFromAppsGrid ()
    {
        _driver.switchTo().frame(0);
        _driver.findElement(By.xpath(GOOGLE_APPS_MAPS)).click();
        return new GoogleMapsPage(_driver);
    }

}
