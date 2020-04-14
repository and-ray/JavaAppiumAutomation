import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

import static java.lang.Thread.sleep;

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
        driver.rotate(ScreenOrientation.PORTRAIT);
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

    @Test
    public void swipeArticleTitleTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "SKIP is absent",
                5);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Appium",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' in search",
                15);
        //System.out.println(driver.getPageSource());
        waitForElementPresent(
                By.xpath("//*[@class='android.view.View']"),
                "Cannot find article title",
                15
        );
        swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article",
                20
        );

    }

    @Test
    public void saveFirstArticleToMyListTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "SKIP is absent",
                5);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                SEARCH_WORD,
                "Cannot find search input",
                5);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);
        waitForElementPresent(
                By.id("pagelib_edit_section_title_description"),
                "Cannot find article title",
                15
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find button to open bookmark options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/onboarding_button'][@text='GOT IT']"),
                "Cannot find button to prove adding bookmarks",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Create new')]"),
                "Cannot find button +",
                5
        );
        String name_of_folder = "MyFirstArticleList";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot find field 'Name of the list' on the page",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find button OK",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_action_overflow_reading_lists'][contains(@text, 'My lists')]"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'NO THANKS')]"),
                "Cannot find button 'NO THANKS'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, '" + name_of_folder + "')]"),
                "Cannot find created folder 'MyFirstArticleList'",
                5
        );
        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find article title",
                15
        );
        swipeElementToTheLeft(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot delete saved article", 10
        );
    }

    @Test
    public void amountOfNotEmptySearchTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "SKIP is absent",
                5);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        String search_line = "Linkin Park discography";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*/*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15
        );
        int amount_of_search_results = getAmountOfElements(By.xpath(search_result_locator));
        Assert.assertTrue("We found too many results!", amount_of_search_results > 0);
    }

    @Test
    public void amountOfEmptySearchTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "SKIP is absent",
                5);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        String search_line = "flgkjdflkgjd flgkjdflkgj dflkgjdlkfg";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*/*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results found']";
        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                15
        );
        assertElementNotPresent(By.xpath(search_result_locator),
                "We've found some results by request " + search_line);
    }

    @Test
    public void changeScreenOrientationOnSearchResultTest() throws InterruptedException {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "SKIP is absent",
                5);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']" +
                        "/*/*[@resource-id='org.wikipedia:id/page_list_item_title']" +
                        "[@text='Java (programming language)']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15);
        String title_before_rotation = waitForElementAndGetAttribute(
                By.xpath("(//*[@resource-id='content']/android.view.View)[1]"),
                "text",
                "Cannot find title of article",
                15);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        sleep(1000);
        String title_after_rotation = waitForElementAndGetAttribute(
                By.xpath("(//*[@resource-id='content']/android.view.View)[1]"),
                "text",
                "Cannot find title of article",
                15);
        Assert.assertEquals(
                "Article title changed after screen rotation",
                title_before_rotation,
                title_after_rotation);
        driver.rotate(ScreenOrientation.PORTRAIT);
        sleep(1000);
        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.xpath("(//*[@resource-id='content']/android.view.View)[1]"),
                "text",
                "Cannot find title of article",
                15);
        Assert.assertEquals(
                "Article title changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    public void checkSearchArticleInBackground() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "SKIP is absent",
                5);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );
        waitForElementPresent(
                By.xpath("(//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "Cannot find article");
        driver.runAppInBackground(2);
        waitForElementPresent(
                By.xpath("(//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "Cannot find article after returning from background");
    }

    @Test
    public void saveTwoArticlesDeleteOneCheckOtherTitleTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "SKIP is absent",
                5);
       //open and save first
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                SEARCH_WORD,
                "Cannot find search input",
                5);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);
        waitForElementPresent(
                By.id("pagelib_edit_section_title_description"),
                "Cannot find article title",
                15
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find button to open bookmark options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/onboarding_button'][@text='GOT IT']"),
                "Cannot find button to prove adding bookmarks",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Create new')]"),
                "Cannot find button +",
                5
        );
        String name_of_folder = "MyJavaList";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot find field 'Name of the list' on the page",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find button OK",
                5
        );

        //get back and save second
        waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot go back to search list",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='JavaScript']"),
                "Cannot find 'JavaScript",
                15
        );
        waitForElementPresent(
                By.id("pagelib_edit_section_title_description"),
                "Cannot find article title",
                15
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find button to open bookmark options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + name_of_folder + "']"),
                "Cannot save article to bookmark list",
                5
        );

        //go to readlist
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_action_overflow_reading_lists'][contains(@text, 'My lists')]"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'NO THANKS')]"),
                "Cannot find button 'NO THANKS'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, '" + name_of_folder + "')]"),
                "Cannot find created folder 'MyFirstArticleList'",
                5
        );
        //check both exist
        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find article title",
                15
        );
        waitForElementPresent(
                By.xpath("//*[contains(@text, 'JavaScript')]"),
                "Cannot find article title",
                15
        );
        //delete 1
        swipeElementToTheLeft(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find saved article"
        );
        //check 1 is deleted
        waitForElementNotPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot delete saved article", 10
        );
        //check 2 exists
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'JavaScript')]"),
                "Cannot find article",
                15
        );
        String article_title = waitForElementAndGetAttribute(
                By.xpath("//android.view.View[@resource-id='content']/android.view.View"),
                "text",
                "article title is absent",
                5
        );
        Assert.assertEquals(
                "article names are different: expected is'JavaScript', and real is '" + article_title+ "'",
                "JavaScript",
                article_title
        );
    }


    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeout_in_seconds){
        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        return element.getAttribute(attribute);
    }

    private void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message  = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    protected void swipeElementToTheLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10
        );

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

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

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }
}
