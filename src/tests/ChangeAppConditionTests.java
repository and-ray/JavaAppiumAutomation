package tests;

import io.appium.java_client.TouchAction;
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
import org.openqa.selenium.Dimension;

import static java.lang.Thread.sleep;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResult() throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);;
        articlePageObject.waitForTitleElement();
        String title_before_rotation = articlePageObject.getArticleTitle();

        rotateScreenLandscape();

        sleep(1000);
        String title_after_rotation = articlePageObject.getArticleTitle();

        assertEquals(
                "Article title changed after screen rotation",
                title_before_rotation,
                title_after_rotation);

        rotateScreenPortrait();
        sleep(1000);
        String title_after_second_rotation = articlePageObject.getArticleTitle();
        assertEquals(
                "Article title changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        sleep(2000);
        backgroundApp(2);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
        navigationUI.moveBack();}
        else {
            ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
            articlePageObject.closeArticle();}
        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        sleep(1000);

        myListPageObject.waitForArticleToAppearByTitle("Java (programming language)");
    }

}
