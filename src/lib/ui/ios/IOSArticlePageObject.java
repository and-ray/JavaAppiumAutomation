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
        MY_ARTICLE_TPL = "xpath://*[contains(@name,'{ARTICLE_DATA}')]";
    }
}
