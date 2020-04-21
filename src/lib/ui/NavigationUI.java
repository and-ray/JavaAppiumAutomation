package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            BUTTON_TO_OPEN_OPTIONS = "//*[@resource-id='org.wikipedia:id/page_action_overflow_reading_lists'][contains(@text, 'My lists')]",
            NO_THANKS_BUTTON = "//*[contains(@text, 'NO THANKS')]",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            NAVIGATE_BACK = "//*[@content-desc='Navigate up']";


    public void openMyLists() {
        openOptions();
        clickMyReadingLists();
        clickNoThanksButton();
    }

    public void moveBack() {
        this.waitForElementAndClick(
                By.xpath(NAVIGATE_BACK),
                "Cannot find button BACK",
                5);
    }

    public void openOptions() {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open options",
                5);
    }

    public void clickMyReadingLists() {
        this.waitForElementAndClick(
                By.xpath(BUTTON_TO_OPEN_OPTIONS),
                "Cannot find button to open article lists",
                5);
    }

    public void clickNoThanksButton() {
        this.waitForElementAndClick(
                By.xpath(NO_THANKS_BUTTON),
                "Cannot find button 'NO THANKS'",
                5);
    }
}
