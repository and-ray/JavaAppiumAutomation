package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "xpath://*[@resource-id='content']/android.view.View",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
            ADD_TO_MY_LIST_OVERLAY = "xpath://*[@resource-id='org.wikipedia:id/onboarding_button'][@text='GOT IT']",
            BUTTON_TO_CREATE_NEW_LIST = "xpath://*[contains(@text, 'Create new')]",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
            MY_FOLDER_FIELD_TPL = "xpath://*[contains(@text, '{ARTICLE_TITLE}')]";

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
        return titleElement.getAttribute("text");
    }

    public void swipeUpToFooter() {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of the article",
                20
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

    public void waitForArticleByTitlePresent(String name_of_article){
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
