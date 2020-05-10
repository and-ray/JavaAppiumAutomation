package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;

public class IOSWelcomePageObject extends WelcomePageObject {
    public IOSWelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    static {
        SKIP_BUTTON = "xpath://*[@name='Skip']";
    }
}
