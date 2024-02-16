import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.beust.jcommander.Strings;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test1
{
    WebDriver driver = null;
    public static Properties config = null;
    public static String status = "passed";
    public static String username = System.getenv("LT_USERNAME");
    public static String access_key = System.getenv("LT_ACCESS_KEY");

    String testURL = "https://lambdatest.github.io/sample-todo-app/";
    String testURLTitle = "Sample page - lambdatest.com";
    @BeforeMethod
    public void testSetUp() throws Exception
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        System.out.println("Started session");
    }

    @Test(description="To Do App on React App")
    public void test1_element_addition_1() throws InterruptedException
    {
        ExtentReports extent = new ExtentReports("target/surefire-reports/html/extentReport.html");
        ExtentTest test1 = extent.startTest("demo application test 1-1", "To Do App test 1");

        driver.get(testURL);
        Thread.sleep(5000);
        test1.log(LogStatus.PASS,"URL is opened");
      //  Thread.sleep(8*60*1000);
        //add
        /* Selenium Java 3.141.59 */
        WebDriverWait wait = new WebDriverWait(driver, 5);
        /* WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); */
        test1.log(LogStatus.PASS, "Wait created");
        /* Click on the Link */
        By textField = By.id("sampletodotext");

        WebElement addText = driver.findElement(textField);

        int item_count = 5;

        for (int i = 1; i <= item_count; i++) {
            addText.click();
            addText.sendKeys("Adding a new item " + i + Keys.ENTER);
            test1.log(LogStatus.PASS, "New item No. " + i + " is added");
            Thread.sleep(2000);
        }

        WebElement temp_element;

        int totalCount = item_count+5;
        int remaining = totalCount-1;

        for (int i = 1; i <= totalCount; i++, remaining--) {

            String xpath = "(//input[@type='checkbox'])["+i+"]";

            driver.findElement(By.xpath(xpath)).click();
            Thread.sleep(500);
            test1.log(LogStatus.PASS, "Item No. " + i + " marked completed");
            By remainingItem = By.className("ng-binding");
            String actualText = driver.findElement(remainingItem).getText();
            String expectedText = remaining+" of "+totalCount+" remaining";

            if (!expectedText.equals(actualText)) {
                test1.log(LogStatus.FAIL, "Wrong Text Description");
                status = "failed";
            }
            Thread.sleep(500);

            test1.log(LogStatus.PASS, "Item No. " + i + " completed");
        }

        /* Once you are outside this code, the list would be empty */
    }

    @AfterMethod
    public void tearDown()
    {
        if (driver != null)
        {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
