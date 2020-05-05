package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class iOSTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String appiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "13.3");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("app", "/Users/user/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");

        driver = new IOSDriver(new URL(appiumURL), capabilities);
        rotateScreenPortrait();
        sleep(5000);

        //SKIP
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage("SKIP is absent\n");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text, 'SKIP')]")));
        element.click();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        driver.quit();
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void backgroundApp(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        }
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }
}
