package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class IOSMyListPageObject extends MyListPageObject {
        public IOSMyListPageObject(AppiumDriver driver) {
            super(driver);
        }

        static {
            BUTTON_TO_OPEN_BOOKMARK_OPTIONS = "id:org.wikipedia:id/article_menu_bookmark";
            FOLDER_BY_NAME_TPL = "xpath://*[contains(@text, '{FOLDER_NAME}')]";
            ARTICLE_BY_NAME_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{ARTICLE_NAME}')]";
        }
}
