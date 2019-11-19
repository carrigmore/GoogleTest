package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class GoogleMapsPage extends GoogleHomePage
{
    public GoogleMapsPage(WebDriver driver)
    {
        super(driver);
    }

    protected final String DIRECTIONS_ICON = "//button[@aria-label='Directions']";
    protected final String GOOGLE_MAP_MENU = "//button[@aria-label='Menu']";
    protected final String GOOGLE_MAP_OPTIONS = "(//label[@class='widget-settings-button-label'])";
    protected final String GOOGLE_MAP_LOGO = "//img[@alt='Google Maps']";

    public void clickGoogleMapsMenu()
    {
        sleep(5);
        WebElement menuIcon = getWebDriver().findElement(By.xpath(GOOGLE_MAP_MENU));
        menuIcon.click();
        waitForMenuToToggle(10);
    }

    public void waitForMenuToToggle (int timeOutSeconds)
    {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), timeOutSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GOOGLE_MAP_LOGO)));
    }

    private List<String> collectGoogleMapOptions ()
    {
        List<String> menuOptionsNames = new ArrayList<String>();
        List <WebElement> menuOptions = getWebDriver().findElements(By.xpath(GOOGLE_MAP_OPTIONS));
        for(WebElement e : menuOptions)
        {
            menuOptionsNames.add(e.getText());
        }
        return menuOptionsNames;
    }

    public void assertGivenOptionsAvailable (String optionName)
    {
        List<String> availableOptionsNames = collectGoogleMapOptions();
        Assert.assertTrue(availableOptionsNames.contains(optionName));
    }

    public void assertGivenOptionsNotAvailable(String optionName)
    {
        List<String> availableOptionsNames = collectGoogleMapOptions();
        Assert.assertFalse(availableOptionsNames.contains(optionName));
    }
}
