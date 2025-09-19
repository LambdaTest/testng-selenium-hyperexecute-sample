import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Test1
{
    RemoteWebDriver driver = null;
    public static String status = "passed";
    public static String username = System.getenv("LT_USERNAME");
    public static String access_key = System.getenv("LT_ACCESS_KEY");

    ExtentSparkReporter spark = new ExtentSparkReporter("target/surefire-reports/html/extentReport.html");
    JsonFormatter json = new JsonFormatter("target/surefire-reports/json/Extent_Report.json");
    ExtentReports extent = new ExtentReports();

//    String testURL = "https://todomvc.com/examples/react/#/";
    String testURL = "https://lambdatest.github.io/sample-todo-app/";
    String testURLTitle = "Sample page - lambdatest.com";
    @BeforeMethod
    @Parameters(value={"browser","version","platform", "resolution"})
    public void testSetUp(String browser, String version, String platform, String resolution) throws Exception
    {
        String platformName = System.getenv("HYPEREXECUTE_PLATFORM") != null ? System.getenv("HYPEREXECUTE_PLATFORM") : platform;
        
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("build", "[HyperExecute - 1] Demonstration of the TestNG Framework");
        capabilities.setCapability("name", "[HyperExecute - 1] Demonstration of the TestNG Framework");
        capabilities.setCapability("platform", platformName);
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("version", version);

        capabilities.setCapability("tunnel",false);
        capabilities.setCapability("network",true);
        capabilities.setCapability("console",true);
        capabilities.setCapability("visual",true);
        capabilities.setCapability("selenium_version", "4.24.0");
        

        capabilities.setCapability("accessibility", true); // Enable accessibility testing
        capabilities.setCapability("accessibility.wcagVersion", "wcag21a"); // Specify WCAG version (e.g., WCAG 2.1 Level A)
        capabilities.setCapability("accessibility.bestPractice", false); // Exclude best practice issues from results
        capabilities.setCapability("accessibility.needsReview", true); // Include issues that need review

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
    public void test1_element_addition_1() throws InterruptedException
    {
        extent.attachReporter(json, spark);
        ExtentTest test1 = extent.createTest("demo application test 1-1", "To Do App test 1");

        driver.get(testURL);
        Thread.sleep(5000);

        test1.log(Status.PASS, "URL is opened");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        test1.log(Status.PASS, "Wait created");

        By textField = By.id("sampletodotext");

        WebElement addText = driver.findElement(textField);

        int item_count = 5;

        for (int i = 1; i <= item_count; i++) {
            addText.click();
            addText.sendKeys("Adding a new item " + i + Keys.ENTER);
            test1.log(Status.PASS, "New item No. " + i + " is added");
            Thread.sleep(2000);
        }

        WebElement temp_element;

        int totalCount = item_count+5;
        int remaining = totalCount-1;

        for (int i = 1; i <= totalCount; i++, remaining--) {

            String xpath = "(//input[@type='checkbox'])["+i+"]";

            driver.findElement(By.xpath(xpath)).click();
            Thread.sleep(500);
            test1.log(Status.PASS, "Item No. " + i + " marked completed");
            By remainingItem = By.className("ng-binding");
            String actualText = driver.findElement(remainingItem).getText();
            String expectedText = remaining+" of "+totalCount+" remaining";

            if (!expectedText.equals(actualText)) {
                test1.log(Status.FAIL, "Wrong Text Description");
                status = "failed";
            }
            Thread.sleep(500);

            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            test1.log(Status.PASS, "Item No. " + i + " completed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot, "sp-test").build());
        }

        extent.flush();

        /* Once you are outside this code, the list would be empty */
    }

    @Test(description="To Do App on React App")
    public void test1_element_addition_2() throws InterruptedException, IOException
    {
        ExtentTest test2 = extent.createTest("demo application test 1-2", "To Do App test 2");

        driver.get(testURL);
        Thread.sleep(5000);

        test2.log(Status.PASS, "URL is opened");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        test2.log(Status.PASS, "Wait created");

        By textField = By.id("sampletodotext");

        WebElement addText = driver.findElement(textField);

        int item_count = 5;

        for (int i = 1; i <= item_count; i++) {
            addText.click();
            addText.sendKeys("Adding a new item " + i + Keys.ENTER);
            test2.log(Status.PASS, "New item No. " + i + " is added");
            Thread.sleep(2000);
        }

        WebElement temp_element;

        int totalCount = item_count+5;
        int remaining = totalCount-1;

        for (int i = 1; i <= totalCount; i++, remaining--) {

            String xpath = "(//input[@type='checkbox'])["+i+"]";

            driver.findElement(By.xpath(xpath)).click();
            Thread.sleep(500);
            test2.log(Status.PASS, "Item No. " + i + " marked completed");
            By remainingItem = By.className("ng-binding");
            String actualText = driver.findElement(remainingItem).getText();
            String expectedText = remaining+" of "+totalCount+" remaining";

            if (!expectedText.equals(actualText)) {
                test2.log(Status.FAIL, "Wrong Text Description");
                status = "failed";
            }
            Thread.sleep(500);

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("target/surefire-reports/json/screenshot.png");
            // Copy the screenshot to destination
            FileUtils.copyFile(srcFile, destFile);
            test2.log(Status.PASS, "Item No. " + i + " completed", MediaEntityBuilder.createScreenCaptureFromPath(destFile.getAbsolutePath(), "sp-test").build());
        }

        extent.flush();

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
