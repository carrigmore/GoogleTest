package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class GoogleSearchResultPage extends GoogleHomePage
{
    protected final String RESULT_STATUS_ALL = "//div[@id='resultStats']";
    protected final String RESULT_CAROUSEL_IMAGE = "//g-scrolling-carousel";
    protected final String RESULT_ALL_ENTRIES = "(//span[@class='st'])";
    protected final String RESULT_IMAGE_ENTRIES = "(//div[@id='rg']//div[@class='rg_bx rg_di rg_el ivg-i']//div[@class='mVDMnf nJGrxf'])";
    protected final String SEARCH_OPTIONS = "(//div[contains(@class,'hdtb-mitem')])";
    protected final String SEARCH_OPTION_ALL = "(//div[contains(@class,'hdtb-mitem')])[1]";
    protected final String SEARCH_OPTION_IMAGES = "(//div[contains(@class,'hdtb-mitem')])[2]";
    protected final String SEARCH_OPTION_MAPS = "(//div[contains(@class,'hdtb-mitem')])[3]";
    protected final String SEARCH_OPTION_NEWS = "(//div[contains(@class,'hdtb-mitem')])[4]";
    protected final String SEARCH_OPTION_VIDEOS = "(//div[contains(@class,'hdtb-mitem')])[5]";

    public GoogleSearchResultPage(WebDriver driver)
    {
        super(driver);
    }

    public enum EntryType {
        ALL,
        NEWS,
        VIDEOS,
        IMAGES,
        MAPS,
        SHOPPING,
        BOOKS,
        FLIGHTS,
        FINANCE
    }

    public void selectSearchImages()
    {
        WebElement imageOption = getWebDriver().findElement(By.xpath(SEARCH_OPTION_IMAGES));
        imageOption.click();
    }

    /**
     * Match the entry case insensitively.
     *
     * @author huibin.zhang
     * @param expectedKeyword expected keyword to display in the result entry
     * @param xPathOfEntries xpath of the entry text
     * @param entryIndex the index of result entry
     *
     *
     * */
    private void assertEntryContainsKeywordByIndex(String expectedKeyword, String xPathOfEntries, int entryIndex)
    {
        WebElement targetTextEntry = getWebDriver().findElement(By.xpath(xPathOfEntries + "[" + entryIndex + "]"));
        String targetText = targetTextEntry.getText();
        // expect the search Term match is based on case insensitive
        Assert.assertTrue(targetText.contains(expectedKeyword)
                || targetText.contains(expectedKeyword.toLowerCase())
                || targetText.contains(expectedKeyword.toUpperCase()));
    }

    /**
     * Assert function can be applied on different search type conducted on Google.
     *
     * @author huibin.zhang
     * @date 15th Nov 2019
     *
     * @param expectedKeyword the expected keyword to display in the result set
     * @param entryType the entry type of search result
     * @param entryIndex the index of entry in the result set
     *
     * */
    public void assertIndexedEntryContainsKeyword(String expectedKeyword, EntryType entryType, int entryIndex)
    {
        switch (entryType)
        {
            case ALL:
                assertEntryContainsKeywordByIndex(expectedKeyword, RESULT_ALL_ENTRIES, entryIndex);
                break;
            case IMAGES:
                assertEntryContainsKeywordByIndex(expectedKeyword, RESULT_IMAGE_ENTRIES, entryIndex);
                break;
            case NEWS:
                //todo needs to implement
            case VIDEOS:
                //todo needs to implement
            case MAPS:
                //todo needs to implement
            case SHOPPING:
                //todo needs to implement
            case BOOKS:
                //todo needs to implement
            case FLIGHTS:
                //todo needs to implement
            case FINANCE:
                //todo needs to implement

        }
    }

}
