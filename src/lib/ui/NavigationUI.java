package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    protected static String
            BUTTON_TO_OPEN_OPTIONS,
            NO_THANKS_BUTTON,
            OPTIONS_BUTTON,
            NAVIGATE_BACK;


    public void openMyLists() {
        openOptions();
        clickMyReadingLists();
        clickNoThanksButton();
    }

    public void moveBack() {
        this.waitForElementAndClick(
                NAVIGATE_BACK,
                "Cannot find button BACK",
                5);
    }

    public void openOptions() {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open options",
                5);
    }

    public void clickMyReadingLists() {
        this.waitForElementAndClick(
                BUTTON_TO_OPEN_OPTIONS,
                "Cannot find button to open article lists",
                5);
    }

    public void clickNoThanksButton() {
        this.waitForElementAndClick(
                NO_THANKS_BUTTON,
                "Cannot find button 'NO THANKS'",
                5);
    }
}
