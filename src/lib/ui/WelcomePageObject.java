package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject{

    private static final String
    STEP_LEARN_MORE_LINK = "Learn more about Wikipedia";
    public WelcomePageObject(AppiumDriver driver){
        super(driver);
    }

    public void waitForLearnMoreLink(){
        this.waitForElementPresent(By.id(STEP_LEARN_MORE_LINK),"Cannot find 'Learn more about Wikipedia' link", 10);
    }
}
