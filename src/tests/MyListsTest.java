package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        String name_of_reading_list = "MyFirstArticleList";
        NavigationUI navigationUI = new NavigationUI(driver);
        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.openBookmarks();
        articlePageObject.addArticleToNewList(name_of_reading_list);
        navigationUI.openMyLists();
        myListPageObject.openFolderByName(name_of_reading_list);
        myListPageObject.waitForArticleToAppearByTitle(name_of_reading_list);
        myListPageObject.swipeByArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticlesDeleteOneCheckOtherTitle() {
        //open and save first
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String name_of_reading_list = "MyFirstArticleList";
        NavigationUI navigationUI = new NavigationUI(driver);
        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.openBookmarks();
        articlePageObject.addArticleToNewList(name_of_reading_list);
        navigationUI.moveBack();
        searchPageObject.clickByArticleWithSubstring("JavaScript");
        articlePageObject.waitForTitleElement();
        myListPageObject.addArticleToExistingReadingList(name_of_reading_list);

        navigationUI.openMyLists();
        myListPageObject.openFolderByName(name_of_reading_list);

        //check both exist
        articlePageObject.waitForArticleByTitlePresent("JavaScript");
        articlePageObject.waitForArticleByTitlePresent("Java (programming language)");

        myListPageObject.swipeByArticleToDelete("Java (programming language)");
        myListPageObject.waitForArticleToDisappearByTitle("Java (programming language)");
        myListPageObject.waitForArticleToAppearByTitle("JavaScript");
    }
}
