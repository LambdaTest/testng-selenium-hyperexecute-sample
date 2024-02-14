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

    String testURL = "https://todomvc.com/examples/react/#/";
    String testURLTitle = "React • TodoMVC";
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
        ExtentTest test1 = extent.startTest("demo application test 1","To Do App test 1");

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
        By elem_new_item_locator = By.xpath("//input[@class='new-todo']");
        WebElement elem_new_item = driver.findElement(elem_new_item_locator);

        /* Add 5 items in the list */
        Integer item_count = 5;

        for (int count = 1; count <= item_count; count++)
        {
            /* Enter the text box for entering the new item */
            elem_new_item.click();
            elem_new_item.sendKeys("Adding a new item " + count + Keys.ENTER);
            test1.log(LogStatus.PASS,"New item No. "+count+" is added");
            Thread.sleep(2000);
        }

            extent.endTest(test1);
            extent.flush();

        WebElement temp_element;

        /* Now that the items are added, we mark the top three items as completed */
        for (int count = 1; count <= item_count; count++)
        {
            Integer fixed_cta_count = 1;

            /* Enter the text box for entering the new item */
            /* Create a varying string to create a new XPath */
            String xpath_str = "//ul[@class='todo-list']/li[" + fixed_cta_count + "]" + "//input[@class='toggle']";
            temp_element = driver.findElement(By.xpath(xpath_str));

            temp_element.click();
            Thread.sleep(2000);
            /* Toggle button to destroy */
            driver.findElement(By.xpath("//li[@class='completed']//button[@class='destroy']")).click();
            Thread.sleep(1000);
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
