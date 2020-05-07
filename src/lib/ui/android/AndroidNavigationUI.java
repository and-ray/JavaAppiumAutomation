package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        BUTTON_TO_OPEN_OPTIONS = "xpath://*[@resource-id='org.wikipedia:id/page_action_overflow_reading_lists'][contains(@text, 'My lists')]";
        NO_THANKS_BUTTON = "xpath://*[contains(@text, 'NO THANKS')]";
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        NAVIGATE_BACK = "xpath://*[@content-desc='Navigate up']";

    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }

}
