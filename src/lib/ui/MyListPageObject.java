package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
            BUTTON_TO_OPEN_BOOKMARK_OPTIONS,
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_NAME_TPL,
            BUTTON_TO_OPEN_SAVED_ARTICLES,
            CLOSE_OVERLAY;

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {
        String name_of_folderXpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                name_of_folderXpath,
                "Cannot find created folder by name" + name_of_folder,
                5
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToTheLeft(
                article_title_xpath,
                "Cannot find saved article with title " + article_title
        );
        if (Platform.getInstance().isIOS()){
            clickElementByTheRightUpperCorner(article_title_xpath,
                    "Cannot find saved article ");
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_title_xpath,
                "Saved article still presents with title" + article_title, 10
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_title_xpath,
                "Cannot find saved article by title" + article_title, 10
        );
    }

    public void openBookmarks() {
        this.waitForElementAndClick(
                BUTTON_TO_OPEN_BOOKMARK_OPTIONS,
                "Cannot find button to open bookmark options",
                5);
    }

    public void openSavedArticles() {
        this.waitForElementAndClick(
                BUTTON_TO_OPEN_SAVED_ARTICLES,
                "Cannot find button to open saved articles",
                5);
    }

    public void addArticleToExistingReadingList(String name_of_reading_list) {
        openBookmarks();
        openFolderByName(name_of_reading_list);
    }

    public void closeOverlay(){
        waitForElementAndClick(CLOSE_OVERLAY, "overlay not found", 15);
    }

    /* TEMPLATES METHODS */

    public String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    public String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_NAME_TPL.replace("{ARTICLE_NAME}", article_title);
    }
    /* TEMPLATES METHODS */

}
