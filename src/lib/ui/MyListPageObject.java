package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {

    private static final String
            BUTTON_TO_OPEN_BOOKMARK_OPTIONS = "org.wikipedia:id/article_menu_bookmark",
            FOLDER_BY_NAME_TPL = "//*[contains(@text, '{FOLDER_NAME}')]",
            ARTICLE_BY_NAME_TPL = "//*[contains(@text, '{ARTICLE_NAME}')]";

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {
        String name_of_folderXpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(name_of_folderXpath),
                "Cannot find created folder by name" + name_of_folder,
                5
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToTheLeft(
                By.xpath(article_title_xpath),
                "Cannot find saved article with title "+ article_title
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_title_xpath),
                "Saved article still presents with title" + article_title, 10
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                By.xpath(article_title_xpath),
                "Cannot find saved article by title" + article_title, 10
        );
    }

    public void openBookmarks() {
        this.waitForElementAndClick(
                By.id(BUTTON_TO_OPEN_BOOKMARK_OPTIONS),
                "Cannot find button to open bookmark options",
                5);
    }
    public void addArticleToExistingReadingList(String name_of_reading_list) {
        openBookmarks();
        openFolderByName(name_of_reading_list);
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
