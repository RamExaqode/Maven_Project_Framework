package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setUp() {
        // Initialize ExtentReports
        ExtentSparkReporter spark = new ExtentSparkReporter("target/test-output/extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Create a test node with the class name
        test = extent.createTest(getClass().getSimpleName());

        // Initialize WebDriver
        driver = DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
    }
}
