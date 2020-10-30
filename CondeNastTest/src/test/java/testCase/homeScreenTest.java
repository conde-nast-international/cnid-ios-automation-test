package testCase;

import pages.WiredHome;
import setup.BaseTest;
import org.testng.annotations.Test;


public class homeScreenTest extends BaseTest {

    @Test
    public void HomescreenTest() throws InterruptedException {
        WiredHome home = new WiredHome(driver);

        try
        {
            home.Verifyhomescreen();
        }
        catch(Exception e)
        {
            System.out.println("Home page is not loaded properly");
        }

    }

    @Test(dependsOnMethods={"HomescreenTest"})
    public void clickWired25Test() throws InterruptedException {
        WiredHome home = new WiredHome(driver);


        home.ClickWiredButton();
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods={"clickWired25Test"})
    public void clickExperimentalTest() throws InterruptedException {
        WiredHome home = new WiredHome(driver);
        home.ClickExperimentaldButton();
        Thread.sleep(5000);
    }


}
