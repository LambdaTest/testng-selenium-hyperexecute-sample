import org.openqa.selenium.WebDriver;
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

public class Test3
{
    WebDriver driver = null;
    public static String status = "passed";
    String username = Test1.username;
    String access_key = Test1.access_key;

    String testURL = "https://todomvc.com/examples/react/#/";
    String testURLTitle = "React • TodoMVC";

    @BeforeMethod
    @Parameters(value={"browser","version","platform", "resolution"})
    public void testSetUp(String browser, String version, String platform, String resolution) throws Exception
    {
        String platformName = System.getenv("HYPEREXECUTE_PLATFORM") != null ? System.getenv("HYPEREXECUTE_PLATFORM") : platform;
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "[HyperExecute - 3] Demonstration of the TestNG Framework");
        capabilities.setCapability("name", "[HyperExecute - 3] Demonstration of the TestNG Framework");

        capabilities.setCapability("platform", System.getenv("HYPEREXECUTE_PLATFORM"));
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("version",version);

        capabilities.setCapability("tunnel",false);
        capabilities.setCapability("network",true);
        capabilities.setCapability("console",true);
        capabilities.setCapability("visual",true);

        try
        {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + access_key + "@hub.lambdatest.com/wd/hub"), capabilities);
        }
        catch (MalformedURLException e)
        {
            System.out.println("Invalid grid URL");
        }
        System.out.println("Started session");
    }

    @Test(description="To Do App on React App")
    public void test3_element_addition_1() throws InterruptedException
    {   ExtentReports extent = new ExtentReports("target/surefire-reports/html/extentReport.html");
        ExtentTest test1 = extent.startTest("demo application test 3","To Do App test 3");
      
        driver.get(testURL);
        Thread.sleep(5000);
        test1.log(LogStatus.PASS,"URL is opened");
        /* Selenium Java 3.141.59 */
        WebDriverWait wait = new WebDriverWait(driver, 5);
         test1.log(LogStatus.PASS, "Wait created");
        /* WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); */

        /* Click on the Link */
        By elem_new_item_locator = By.xpath("//input[@class='new-todo']");
        WebElement elem_new_item = driver.findElement(elem_new_item_locator);

        /* Add 5 items in the list */
        Integer item_count = 15;

        for (int count = 1; count <= item_count; count++)
        {
            /* Enter the text box for entering the new item */
            elem_new_item.click();
            elem_new_item.sendKeys("Adding a new item " + count + Keys.ENTER);
            test1.log(LogStatus.PASS,"New item No. "+count+" is added");
            Thread.sleep(2000);
        }

        WebElement temp_element;
        extent.endTest(test1);
        extent.flush();
        /* Now that the items are added, we mark the top three items as completed */
        for (int count = 1; count <= item_count; count++)
        {
            Integer fixed_cta_count = 1;

            /* Enter the text box for entering the new item */
            /* Create a varying string to create a new XPath */
            /* String xpath_str = "//ul[@class='todo-list']/li[" + count + "]" + "//input[@class='toggle']"; */
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
