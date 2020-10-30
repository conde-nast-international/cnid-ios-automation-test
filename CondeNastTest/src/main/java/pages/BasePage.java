package pages;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.Map;
import java.util.HashMap;

import io.appium.java_client.TouchAction;

public class BasePage {

    public AppiumDriver driver;
    public WebDriverWait wait;


    //Constructor
    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,30);
    }

    void click(By element) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }

    void swipe() throws InterruptedException {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "up");
        driver.executeScript("mobile: swipe", params);
    }

    String getText(By element) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        return driver.findElement(element).getText();
    }

    boolean isEnableElement(By element) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        Assert.assertTrue(driver.findElement(element).isEnabled());
        return true;
    }

    void assertText(By element, String text) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        Assert.assertEquals(driver.findElement(element).getText(),"");
    }
}
