package base;

import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import utils.DriverFactory;
import utils.ExtentTestManager;

import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setupReport() {
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(new com.aventstack.extentreports.reporter.ExtentSparkReporter("target/test-output/extentReport.html"));
        ExtentTestManager.setExtent(extent);
    }

    @BeforeMethod
    public void setUp(Method method) {
        ExtentTestManager.startTest(method.getName());
        driver = DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void flushReport() {
        ExtentTestManager.getExtent().flush();
    }
}
