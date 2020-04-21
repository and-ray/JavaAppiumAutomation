package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
            SEARCH_INPUT = "search_src_text",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']",
            TITLE_BY_SUBSTRING_TPL = "//android.view.View/android.view.View[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT_WITH_TITLE = "//*[@resource-id='org.wikipedia:id/search_results_list']/*/*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(
                By.id(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element",
                5);
        this.waitForElementPresent(
                By.id(SEARCH_INIT_ELEMENT),
                "Cannot find search after clicking search init element",
                15);

    }

    public String getSearchFieldText() {
        String titleElement = this.waitForElementAndGetAttribute(
                By.id(SEARCH_INPUT),
                "text",
                "Cannot find field for searching",
                15
        );
        return titleElement;
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(
                By.id(SEARCH_INPUT),
                search_line,
                "Cannot find and type into search input",
                5);

    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                By.xpath(search_result_xpath),
                "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                By.xpath(search_result_xpath),
                "Cannot find and click search result with substring " + substring,
                10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find search cancel button",
                5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Search cancel button still presents",
                5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find and click search cancel button",
                5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT_WITH_TITLE),
                "Cannot find anything by the request ",
                15
        );
        return getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT_WITH_TITLE));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result element",
                15
        );
    }

    public int countElementsPresentOnSearchPage() {
        return countElementsPresent(By.xpath(SEARCH_RESULT_ELEMENT_WITH_TITLE),
                "Results of search are absent on the page",
                10);
    }

    public List<WebElement> getElementsPresentOnSearchPage() {
        return waitForElementsPresent(By.xpath(SEARCH_RESULT_ELEMENT_WITH_TITLE),
                "Results of search are absent on the page",
                10
        );
    }

    public boolean countElementsNotPresentOnSearchPage() {
        return waitForElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT_WITH_TITLE),
                "Results of search exist on the page",
                5
        );
    }

    /*ASSERTS*/
    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT_WITH_TITLE),
                "We supposed not to find any result");
    }

    /*ASSERTS*/

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */
}
