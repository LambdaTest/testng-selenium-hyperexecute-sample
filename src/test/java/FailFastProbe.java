import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class FailFastProbe {
  RemoteWebDriver driver = null;
  public static String username = System.getenv("LT_USERNAME");
  public static String access_key = System.getenv("LT_ACCESS_KEY");

  private void openSession(String testName) throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("build", "Global Policies :: failFast probe");
    capabilities.setCapability("name", testName);
    capabilities.setCapability("platform", "LINUX");
    capabilities.setCapability("browserName", "Chrome");
    capabilities.setCapability("version", "latest");
    capabilities.setCapability("selenium_version", "4.19.0");
    driver = new RemoteWebDriver(
        new URL("https://" + username + ":" + access_key + "@hub.lambdatest.com/wd/hub"),
        capabilities);
    System.out.println("Started session for " + testName);
  }

  @Test(description = "failFast probe test 1")
  public void ff_test_1() throws Exception {
    openSession("ff_test_1");
    Assert.fail("Intentional failure for failFast probe");
  }

  @Test(description = "failFast probe test 2")
  public void ff_test_2() throws Exception {
    openSession("ff_test_2");
    Assert.fail("Intentional failure for failFast probe");
  }

  @Test(description = "failFast probe test 3")
  public void ff_test_3() throws Exception {
    openSession("ff_test_3");
    Assert.fail("Intentional failure for failFast probe");
  }

  @Test(description = "failFast probe test 4")
  public void ff_test_4() throws Exception {
    openSession("ff_test_4");
    Assert.fail("Intentional failure for failFast probe");
  }

  @Test(description = "failFast probe test 5")
  public void ff_test_5() throws Exception {
    openSession("ff_test_5");
    Assert.fail("Intentional failure for failFast probe");
  }

  @Test(description = "failFast probe test 6")
  public void ff_test_6() throws Exception {
    openSession("ff_test_6");
    Assert.fail("Intentional failure for failFast probe");
  }

  @AfterMethod
  public void tearDown() {
    if (driver != null) {
      try {
        ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
      } catch (Exception ignored) {}
      driver.quit();
    }
  }
}
