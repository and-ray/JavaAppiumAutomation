package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RESULT_ELEMENT_WITH_TITLE;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                5);
        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find search after clicking search init element",
                15);

    }

    public String getSearchFieldText() {
        String titleElement = this.waitForElementAndGetAttribute(
                SEARCH_INPUT,
                "text",
                "Cannot find field for searching",
                15
        );
        return titleElement;
    }


    public String getSearchFieldName() {
        String titleElement = this.waitForElementAndGetAttribute(
                SEARCH_INPUT,
                "name",
                "Cannot find field for searching",
                15
        );
        return titleElement;
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                search_line,
                "Cannot find and type into search input",
                5);

    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElementBySubstring(substring);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result with substring " + substring);
    }

    public void waitForSearchResultByTitle(String substring) {
        String search_result_xpath = getResultSearchElementByTitle(substring);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result ");

    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result with title = " + title + ", description = " + description);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElementBySubstring(substring);
        this.waitForElementAndClick(
                search_result_xpath,
                "Cannot find and click search result with substring " + substring,
                10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button",
                5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button still presents",
                5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button",
                5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element",
                15
        );
    }

    public int countElementsPresentOnSearchPage() {
        return countElementsPresent(SEARCH_RESULT_ELEMENT,
                "Results of search are absent on the page",
                10);
    }

    public List<WebElement> getElementsPresentOnSearchPage() {
        return waitForElementsPresent(SEARCH_RESULT_ELEMENT,
                "Results of search are absent on the page",
                10
        );
    }

    public boolean countElementsNotPresentOnSearchPage() {
        return waitForElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "Results of search exist on the page",
                5
        );
    }
    public boolean countElementsBySubstringNotPresentOnSearchPage(String substring) {
        String locator = getResultSearchElementBySubstring(substring);
        return waitForElementNotPresent(
                locator,
                "Results of search exist on the page",
                5
        );
    }

    /*ASSERTS*/
    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT,
                "We supposed not to find any result");
    }

    /*ASSERTS*/

    /* TEMPLATES METHODS */
    private static String getResultSearchElementByTitle(String substring) {
        return SEARCH_RESULT_ELEMENT_WITH_TITLE.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementBySubstring(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndDescription(String title, String description) {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */
}
