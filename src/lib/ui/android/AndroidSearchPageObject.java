package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container";
                SEARCH_INPUT = "id:search_src_text";
                SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']";
                SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL =
                        "xpath://android.view.ViewGroup[./android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']]" +
                         "[./android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']]";
                SEARCH_RESULT_ELEMENT =
                        "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*/*[@resource-id='org.wikipedia:id/page_list_item_title']";
                SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

}
