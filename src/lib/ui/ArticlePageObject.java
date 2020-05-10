package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            ADD_TO_MY_LIST_OVERLAY,
            BUTTON_TO_CREATE_NEW_LIST,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            MY_FOLDER_FIELD_TPL,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            CLOSE_ARTICLE_BUTTON;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return waitForElementPresent(
                TITLE,
                "Cannot find article title on the page",
                15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getAttribute("text");
        } else {
            return titleElement.getAttribute("name");
        }
    }

    public void swipeUpToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    100
            );
        } else swipeUpTillElementAppears(
                FOOTER_ELEMENT,
                "Cannot find the end of the article",
                40
        );
    }

    public void addArticleToNewList(String name_of_reading_list) {
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find button to prove adding bookmarks",
                5);
        this.waitForElementAndClick(
                BUTTON_TO_CREATE_NEW_LIST,
                "Cannot find button +",
                5);
        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_reading_list,
                "Cannot find field 'Name of the list' on the page",
                5);
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find button OK",
                5);
    }

    public void addArticlesToMySaved(){
        waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
                );
    }

    public void closeArticle(){
        waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                "Cannot find button to close article",
                5
        );
    }

    public void waitForArticleByTitlePresent(String name_of_article) {
        String articleXpath = getArticleXpathByName(name_of_article);
        waitForElementPresent(
                articleXpath,
                "Cannot find article title",
                15
        );
    }

    /* TEMPLATES METHODS */

    public String getArticleXpathByName(String name_of_article) {
        return MY_FOLDER_FIELD_TPL.replace("{ARTICLE_TITLE}", name_of_article);
    }
    /* TEMPLATES METHODS */

}
