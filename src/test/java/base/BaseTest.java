package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import utils.DriverFactory;
import utils.ExtentTestManager;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent;

    @BeforeSuite
    public void createScreenshotFolder() {
        File screenshotDir = new File("target/screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
            System.out.println("Created screenshot directory: " + screenshotDir.getAbsolutePath());
        }
    }

    @BeforeClass
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/test-output/extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTestManager.setExtent(extent);
    }

    @BeforeMethod
    public void setUp(Method method) {
        driver = DriverFactory.getDriver();
        ExtentTestManager.startTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String path = "target/screenshots/" + result.getName() + "_" + timestamp + ".png";
                FileUtils.copyFile(screenshot, new File(path));
                ExtentTestManager.getTest().addScreenCaptureFromPath(path);
                ExtentTestManager.getTest().fail("Test failed. Screenshot attached.");
                System.out.println("Screenshot saved: " + path);
            } catch (Exception e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }

        DriverFactory.quitDriver();
    }

    @AfterClass
    public void flushReport() {
        ExtentTestManager.getExtent().flush();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public ExtentTest getTest() {
        return ExtentTestManager.getTest();
    }
}
