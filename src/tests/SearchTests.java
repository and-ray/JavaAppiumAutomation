package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();
        assertTrue("We found too many results!", amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_line = "flgkjdflkgjd flgkjdflkgj dflkgjdlkfg";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testCheckIfSearchFieldPresents() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchFieldTitle;
        if (Platform.getInstance().isAndroid()){
        searchFieldTitle = searchPageObject.getSearchFieldText();}
        else {searchFieldTitle = searchPageObject.getSearchFieldName();}
        assertEquals("We see unexpected title!", "Search Wikipedia", searchFieldTitle);
        searchPageObject.typeSearchLine("Java");
    }

    @Test
    public void testCheckArticlesAppearAndDisappearInSearchResult() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        int resultOfArticle = searchPageObject.countElementsPresentOnSearchPage();
        assertTrue("We don't see few articles in search result",
                resultOfArticle > 1);
        searchPageObject.clickCancelSearch();
        boolean articlesAreAbsent;
        if(Platform.getInstance().isAndroid()){
            articlesAreAbsent = searchPageObject.countElementsNotPresentOnSearchPage();}
        else {articlesAreAbsent = searchPageObject.countElementsBySubstringNotPresentOnSearchPage("Java");}
        assertTrue("We see few articles in search result", articlesAreAbsent);
    }

    @Test
    public void testCheckSearchWordInSearchResults() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_word = "java";
        searchPageObject.typeSearchLine(search_word);
        List<WebElement> searchResult = searchPageObject.getElementsPresentOnSearchPage();

        // bad practice
        for (int i = 0; i < searchResult.size(); i++) {
            String lineToCompare;
            if (Platform.getInstance().isAndroid()){
            lineToCompare = searchResult.get(i).getAttribute("text").toLowerCase();}
            else {
                lineToCompare = searchResult.get(i).getAttribute("name").toLowerCase();
            }
            assertTrue("We don't see search word " + search_word
                            + " in the article title for the line: " + lineToCompare,
                    lineToCompare.contains(search_word));
        }
    }

    @Test
    public void testCheckTitleAndDescriptionInSearchResults() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_word = "java";
        searchPageObject.typeSearchLine(search_word);
        if (Platform.getInstance().isAndroid()){
        searchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
        searchPageObject.waitForElementByTitleAndDescription("JavaScript", "High-level programming language");
        searchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
          }
        else {
            searchPageObject.waitForSearchResult("Island of Indonesia");
            searchPageObject.waitForSearchResult("High-level programming language");
            searchPageObject.waitForSearchResult("Object-oriented programming language");
            }
        }

    @Test
    public void testCancelSearchAndClearField() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("appium");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }
}
