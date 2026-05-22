import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;

public class Test3 {
    WebDriver driver = null;
    public static String status = "passed";
    String username = Test1.username;
    String access_key = Test1.access_key;

    // String testURL = "https://todomvc.com/examples/react/#/";
    String testURL = "https://ltqa-frontend.lambdatestinternal.com/sample-todo-app/";
    String testURLTitle = "Sample page - lambdatest.com";

    ExtentSparkReporter spark = new ExtentSparkReporter("target/surefire-reports/html/extentReport.html");
    JsonFormatter json = new JsonFormatter("target/surefire-reports/json/Extent_Report.json");
    ExtentReports extent = new ExtentReports();

    @BeforeMethod
    @Parameters(value = { "browser", "version", "platform", "resolution" })
    public void testSetUp(String browser, String version, String platform, String resolution) throws Exception {
        String platformName = System.getenv("HYPEREXECUTE_PLATFORM") != null ? System.getenv("HYPEREXECUTE_PLATFORM")
                : platform;

        // LambdaTest specific options using W3C protocol (LT:Options)
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "[HyperExecute - 3] Demonstration of the TestNG Framework");
        ltOptions.put("name", "[HyperExecute - 3] Demonstration of the TestNG Framework");
        ltOptions.put("platformName", platformName);
        ltOptions.put("tunnel", false);
        ltOptions.put("network", true);
        ltOptions.put("console", true);
        ltOptions.put("visual", true);
        ltOptions.put("selenium_version", "4.24.0");
        ltOptions.put("w3c", true);

        // Accessibility options
        ltOptions.put("accessibility", true);
        ltOptions.put("accessibility.wcagVersion", "wcag21a");
        ltOptions.put("accessibility.bestPractice", false);
        ltOptions.put("accessibility.needsReview", true);

        // Use browser-specific Options class for W3C compliance
        MutableCapabilities browserOptions;
        switch (browser.toLowerCase()) {
            case "chrome":
                browserOptions = new ChromeOptions();
                break;
            case "microsoftedge":
            case "edge":
                browserOptions = new EdgeOptions();
                break;
            case "firefox":
                browserOptions = new FirefoxOptions();
                break;
            default:
                browserOptions = new ChromeOptions();
        }

        browserOptions.setCapability("browserVersion", version);
        browserOptions.setCapability("platformName", platformName);
        browserOptions.setCapability("LT:Options", ltOptions);

        try {
            driver = new RemoteWebDriver(
                    new URL("https://" + username + ":" + access_key + "@hub.lambdatest.com/wd/hub"), browserOptions);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        }
        System.out.println("Started session");
    }

    @Test(description = "To Do App on React App")
    public void test3_element_addition_1() throws InterruptedException {
        ExtentTest test1 = extent.createTest("demo application test 3-1", "To Do App test 1");

        driver.get(testURL);
        Thread.sleep(5000);

        test1.log(Status.PASS, "URL is opened");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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

        int totalCount = item_count + 5;
        int remaining = totalCount - 1;

        for (int i = 1; i < totalCount; i++, remaining--) {

            String xpath = "(//input[@type='checkbox'])[" + i + "]";

            driver.findElement(By.xpath(xpath)).click();
            Thread.sleep(500);
            test1.log(Status.PASS, "Item No. " + i + " marked completed");
            By remainingItem = By.className("ng-binding");
            String actualText = driver.findElement(remainingItem).getText();
            String expectedText = remaining + " of " + totalCount + " tasks remaining";

            if (!actualText.contains(expectedText)) {
                test1.log(Status.FAIL, "Wrong Text Description");
                System.out.println("unmatched at " + expectedText + " " + actualText);
                status = "failed";
            }
            Thread.sleep(500);

            test1.log(Status.PASS, "Item No. " + i + " completed");
        }

        extent.flush();

        /* Once you are outside this code, the list would be empty */
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
