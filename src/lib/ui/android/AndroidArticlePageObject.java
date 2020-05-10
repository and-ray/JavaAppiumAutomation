package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    static {
        TITLE = "xpath://*[@resource-id='content']/android.view.View";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        ADD_TO_MY_LIST_OVERLAY = "xpath://*[@resource-id='org.wikipedia:id/onboarding_button'][@text='GOT IT']";
        BUTTON_TO_CREATE_NEW_LIST = "xpath://*[contains(@text, 'Create new')]";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        MY_ARTICLE_TPL = "xpath://*[contains(@text, '{ARTICLE_DATA}')]";
    }
}
