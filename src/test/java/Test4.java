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

public class Test4
{
    WebDriver driver = null;
    public static String status = "passed";
    String username = Test1.username;
    String access_key = Test1.access_key;

//    String testURL = "https://todomvc.com/examples/react/#/";
    String testURL = "https://takitajwar17.github.io/Simple-Todo-List-Refactored/";
    String testURLTitle = "React • TodoMVC";

    @BeforeMethod
    @Parameters(value={"browser","version","platform", "resolution"})
    public void testSetUp(String browser, String version, String platform, String resolution) throws Exception
    {
        String platformName = System.getenv("HYPEREXECUTE_PLATFORM") != null ? System.getenv("HYPEREXECUTE_PLATFORM") : platform;
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "[HyperExecute - 4] Demonstration of the TestNG Framework");
        capabilities.setCapability("name", "[HyperExecute - 4] Demonstration of the TestNG Framework");

        capabilities.setCapability("platform", System.getenv("HYPEREXECUTE_PLATFORM"));
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("version", version);

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
    public void test4_element_addition_1() throws InterruptedException
    {   ExtentReports extent = new ExtentReports("target/surefire-reports/html/extentReport.html");
        ExtentTest test1 = extent.startTest("demo application test 1", "To Do App test 1");

        driver.get(testURL);
        Thread.sleep(5000);

        test1.log(LogStatus.PASS, "URL is opened");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        test1.log(LogStatus.PASS, "Wait created");

        By textField = By.xpath("//input[contains(@class,'input input-bordered')]");

        WebElement addText = driver.findElement(textField);

        int item_count = 5;

        for (int i = 1; i <= item_count; i++) {
            addText.click();
            addText.sendKeys("Adding a new item " + i + Keys.ENTER);
            test1.log(LogStatus.PASS, "New item No. " + i + " is added");
            Thread.sleep(2000);
        }

        WebElement temp_element;

        for (int i = 1; i <= item_count; i++) {

            driver.findElement(By.xpath("//i[contains(@class,'bx bx-check')]")).click();
            Thread.sleep(1000);
            test1.log(LogStatus.PASS, "Item No. " + i + " marked completed");
            driver.findElement(By.xpath("//i[contains(@class,'bx bx-trash')]")).click();
            Thread.sleep(1000);
            test1.log(LogStatus.PASS, "Item No. " + i + " deleted");
        }

        extent.endTest(test1);
        extent.flush();

        /* Once you are outside this code, the list would be empty */
    }

    @AfterMethod
    public void tearDown()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }
}
