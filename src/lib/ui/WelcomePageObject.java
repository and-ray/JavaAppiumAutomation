package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_LEARN_MORE_LINK = "Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LINK = "Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "Learn more about data collected",
            GET_STARTED_BUTTON = "Get started",
            NEXT_BUTTON = "Next";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(By.id(STEP_LEARN_MORE_LINK), "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(By.id(STEP_NEW_WAYS_TO_EXPLORE_TEXT), "Cannot find 'New ways to explore' link", 10);
    }

    public void waitForAddOrEditPrefferedLangText() {
        this.waitForElementPresent(By.id(STEP_ADD_OR_EDIT_PREFERRED_LINK), "Cannot find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(By.id(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK), "Cannot find 'Get started' button", 10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(By.id(NEXT_BUTTON), "Cannot find and click 'Next' link", 10);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(By.id(GET_STARTED_BUTTON), "Cannot find and click 'Next' link", 10);
    }
}
