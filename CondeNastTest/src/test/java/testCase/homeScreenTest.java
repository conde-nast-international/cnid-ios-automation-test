package testCase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.WiredHome;
import setup.BaseTest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class homeScreenTest extends BaseTest {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;
    File reportDir = new File(System.getProperty("user.dir"));

    String reportFilePath = reportDir.toString() + "/ExtentReports/";

    @BeforeClass
    public void beforeClass() {

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-results/CondeNastExtentReport.html");
        // Create an object of Extent Reports
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "Krishna MacBook Pro");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("User Name", "Krishna chetan");
        htmlReporter.config().setDocumentTitle("Conde Nast Demo ");
        // Name of the report
        htmlReporter.config().setReportName("Wired Tests");
        // Dark Theme
        htmlReporter.config().setTheme(Theme.STANDARD);
    }

    //This method is to capture the screenshot and return the path of the screenshot.
    public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        // after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }


    @Test
    public void HomeScreenTest() throws InterruptedException {
        WiredHome home = new WiredHome(driver);
        logger = extent.createTest("HomescreenTest");

        try
        {
            home.Verifyhomescreen();
            logger.createNode("Home screen  is loaded successfully ");
        }
        catch(Exception e)
        {
            logger.createNode("Home screen  is loaded successfully ");
        }

    }

    @Test(dependsOnMethods ="HomeScreenTest")
    public void clickWired25Test() throws InterruptedException {
        WiredHome home = new WiredHome(driver);

        try
        {
            home.ClickWiredButton();
            logger.createNode("Wired Button  is clicked successfully ");
        }
        catch(Exception e)
        {
            logger.createNode("Unable to click Wired Button");
        }




        Thread.sleep(5000);
    }

    @Test(dependsOnMethods ="clickWired25Test")
    public void clickExperimentalTest() throws InterruptedException {
        WiredHome home = new WiredHome(driver);
        try
        {
            home.ClickExperimentaldButton();
            logger.createNode("Experimental Button  is clicked successfully ");

        }
        catch(Exception e)
        {
            logger.createNode("Unable to click Experimental Button");
        }
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods ="clickExperimentalTest")
    public void clickDummyButtonFailTest() throws InterruptedException {
        WiredHome home = new WiredHome(driver);

            home.DummyButton();
            logger.createNode("Dummy button test ");

    }
    
    @AfterMethod
    public void getResult(ITestResult result) throws Exception{
        if(result.getStatus() == ITestResult.FAILURE){
            //MarkupHelper is used to display the output in different colors
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
            //To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
            //We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method.
            //String Scrnshot=TakeScreenshot.captuerScreenshot(driver,"TestCaseFailed");
            String screenshotPath = getScreenShot(driver, result.getName());
            //To add it in the extent report
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
        }
        else if(result.getStatus() == ITestResult.SKIP){
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
    }

    @AfterTest
    public void endReport() {
        extent.flush();
    }
}
