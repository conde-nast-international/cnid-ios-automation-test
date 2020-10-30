package setup;

import io.appium.java_client.service.local.AppiumDriverLocalService;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StartAppium extends BaseTest {


    private AppiumDriverLocalService service;

    @BeforeClass
    public void setUp() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }

    @Test
    public void getAppiumState() {
        System.out.println(service.isRunning());
    }

    @AfterClass
    public void tearDown() {
        service.stop();
    }
}