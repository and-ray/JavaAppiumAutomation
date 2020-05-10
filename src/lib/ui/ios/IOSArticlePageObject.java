package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        /*ADD_TO_MY_LIST_OVERLAY = "xpath://*[@resource-id='org.wikipedia:id/onboarding_button'][@text='GOT IT']";
        BUTTON_TO_CREATE_NEW_LIST = "xpath://*[contains(@text, 'Create new')]";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        MY_FOLDER_FIELD_TPL = "xpath://*[contains(@text, '{ARTICLE_TITLE}')]";
         */
    }
}
