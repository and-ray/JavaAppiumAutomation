package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {

    public static String name_of_reading_list = "MyFirstArticleList";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        myListPageObject.openBookmarks();
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToNewList(name_of_reading_list);
        } else {
            articlePageObject.addArticlesToMySaved();
        }
        navigationUI.openMyLists();
        if (Platform.getInstance().isAndroid()) {
        myListPageObject.openFolderByName(name_of_reading_list);
        myListPageObject.waitForArticleToAppearByTitle(name_of_reading_list);
        }
        myListPageObject.swipeByArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticlesDeleteOneCheckOtherTitle() {
        //open and save first
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
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
