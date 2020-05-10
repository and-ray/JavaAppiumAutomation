package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;

public class AndroidWelcomePageObject extends WelcomePageObject {
    public AndroidWelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    static {SKIP_BUTTON = "xpath://*[@text='SKIP']";}
}
