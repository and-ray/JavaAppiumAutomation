package tests;

import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import static java.lang.Thread.sleep;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResult() throws InterruptedException {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
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
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        sleep(2000);
        backgroundApp(2);
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.moveBack();
        MyListPageObject myListPageObject = new MyListPageObject(driver);
        sleep(1000);
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();

       // action.tap((int)(size.width * 0.2),(int)(size.height * 0.8) ).perform();

        myListPageObject.waitForArticleToAppearByTitle("Java (programming language)");
    }

}
