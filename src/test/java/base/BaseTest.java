package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utils.DriverFactory;
import utils.ExtentManager;
import utils.ExtentTestManager;
import utils.ScreenshotUtil;

import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;

    @BeforeSuite
    public void startReport() {
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setUp(Method method) {
        driver = DriverFactory.getDriver();
        ExtentTest test = extent.createTest(method.getName());
        ExtentTestManager.setTest(test);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            ExtentTestManager.getTest().fail(result.getThrowable());
            ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentTestManager.getTest().pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().skip("Test skipped");
        }

        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}
