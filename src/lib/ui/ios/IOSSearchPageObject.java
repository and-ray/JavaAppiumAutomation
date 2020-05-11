package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    static {

        SEARCH_INIT_ELEMENT = "xpath://*[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://*[@name='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL =
                "xpath://*[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL =
                "xpath://android.view.ViewGroup[./android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']]" +
                        "[./android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_RESULT_ELEMENT_WITH_TITLE = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
            //    "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*/*[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@name='No results found']";
    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
