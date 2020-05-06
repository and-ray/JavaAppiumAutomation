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

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String appiumURL = "http://127.0.0.1:4723/wd/hub";
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = getCapabilitiesByPlatformEnv();
        driver = getWebDriverByPlatformEnv(capabilities);
        rotateScreenPortrait();
        sleep(5000);

        /*
         //SKIP
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage("SKIP is absent\n");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text, 'SKIP')]")));
        element.click();
        */

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
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");// last iOS version
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "E:\\And-Ray\\it\\mobile\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("platformVersion", "13.3");
            capabilities.setCapability("deviceName", "iPhone SE");
            capabilities.setCapability("app", "/Users/user/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        } else throw new Exception("Cannot get run platform from environment variable. Platform value = " + platform);
        return capabilities;
    }

    private AppiumDriver getWebDriverByPlatformEnv(DesiredCapabilities capabilities) throws Exception {
        String platform = System.getenv("PLATFORM");
        if (platform.equals(PLATFORM_ANDROID)) {
            driver = new AndroidDriver(new URL(appiumURL), capabilities);
        } else if (platform.equals(PLATFORM_IOS)) {
            driver = new IOSDriver(new URL(appiumURL), capabilities);
        } else throw new Exception("Cannot get run webDriver from environment variable. Platform value = " + platform);
        return driver;
    }
}
