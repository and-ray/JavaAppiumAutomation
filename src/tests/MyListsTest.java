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

        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openBookmarks();
            articlePageObject.addArticleToNewList(name_of_reading_list);
            navigationUI.openMyLists();
            myListPageObject.openFolderByName(name_of_reading_list);
            myListPageObject.waitForArticleToAppearByTitle(name_of_reading_list);
        } else {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.closeArticle();
            searchPageObject.clickCancelSearch();
            myListPageObject.openSavedArticles();
            myListPageObject.closeOverlay();
        }
        myListPageObject.swipeByArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticlesDeleteOneCheckOtherTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openBookmarks();
            articlePageObject.addArticleToNewList(name_of_reading_list);
            navigationUI.moveBack();
        } else {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.closeArticle();
        }
        searchPageObject.clickByArticleWithSubstring("JavaScript");

        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openBookmarks();
            myListPageObject.addArticleToExistingReadingList(name_of_reading_list);
            navigationUI.openMyLists();
            myListPageObject.openFolderByName(name_of_reading_list);

            articlePageObject.waitForArticleByTitlePresent("JavaScript");
            articlePageObject.waitForArticleByTitlePresent("Java (programming language)");
        } else {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.closeArticle();
            searchPageObject.clickCancelSearch();
            myListPageObject.openSavedArticles();
            myListPageObject.closeOverlay();
            articlePageObject.waitForArticleByNamePresent("Java (programmin");
            articlePageObject.waitForArticleByNamePresent("JavaScript");
        }
        myListPageObject.swipeByArticleToDelete("Java (programming language)");
        myListPageObject.waitForArticleToDisappearByTitle("Java (programming language)");
        myListPageObject.waitForArticleToAppearByTitle("JavaScript");
    }
}
