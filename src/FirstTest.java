import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;
    private String SEARCH_WORD = "JAVA";

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");// last iOS version
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "E:\\And-Ray\\it\\mobile\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                SEARCH_WORD,
                "Cannot find search input",
                5);
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);
    }

    @Test
    public void checkIfSearchFieldPresentsTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);
        WebElement titleElement = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find field for searching",
                15
        );
        String searchFieldTitle = titleElement.getAttribute("text");
        Assert.assertEquals("We see unexpected title!", "Search…", searchFieldTitle);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                SEARCH_WORD,
                "Cannot find search input",
                5);
    }

    @Test
    public void checkArticlesAppearAndDisappearInSearchResultTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                SEARCH_WORD,
                "Cannot find search input",
                5);

        int resultOfArticle = countElementsPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Results of search are absent on the page",
                5
        );

        Assert.assertTrue("We don't see few articles in search result",
                resultOfArticle > 1);


        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5);

        boolean articlesAreAbsent = waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Results of search are absent on the page",
                5
        );

        Assert.assertTrue("We see few articles in search result",
                articlesAreAbsent);
    }

    @Test
    public void checkSearchWordInSearchResultsTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                SEARCH_WORD,
                "Cannot find search input",
                5
        );

        List<WebElement> searchResult = findElementsOnSearchPage(By.id("org.wikipedia:id/page_list_item_title"),
                "Results of search are absent on the page",
                5
        );

        // i know it's bad =(

        for (int i = 0; i < searchResult.size(); i++) {
            String lineToCompare = searchResult.get(i).getAttribute("text").toLowerCase();
            Assert.assertTrue("We don't see search word " + SEARCH_WORD.toLowerCase()
                    + " in the article title for the line: " + lineToCompare,
                    lineToCompare.contains(SEARCH_WORD.toLowerCase()));
        }
    }

    @Test
    public void cancelSearchTest() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5);
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still presented on the page",
                5
        );
    }

    @Test
    public void cancelSearchAndClearFieldTest() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                SEARCH_WORD,
                "Cannot find search input",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still presented on the page",
                5
        );
    }

    @Test
    public void compareArticleTitleTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                SEARCH_WORD,
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);
        WebElement titleElement = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        String articleTitle = titleElement.getAttribute("text");
        Assert.assertEquals("We see unexpected title!", "Java (programming language)", articleTitle);

    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private int countElementsPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        List<WebElement> result = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return result.size();

    }

    private List<WebElement> findElementsOnSearchPage(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));

    }

    private WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 5);
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

}
