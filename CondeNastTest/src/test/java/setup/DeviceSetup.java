package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class DeviceSetup extends BaseTest {


    static AppiumDriver prepareDevice() throws MalformedURLException {
        File rootDirectory = new File(System.getProperty("user.dir"));
        File appDir = new File(rootDirectory, "/app/");
        File app = new File(appDir, "WIRED.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability("deviceName", "ce0317138187a0860c");
        capabilities.setCapability("autoAcceptAlerts",true);
        capabilities.setCapability("noReset",false);
        capabilities.setCapability("autoGrantPermissions",true);
        //capabilities.setCapability("appPackage", "kc.example");
        //capabilities.setCapability("appWaitActivity","kc.example");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10000);
        driver = new AndroidDriver (new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }
}

