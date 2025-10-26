package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    private static WebDriver driver;
    static Logger log = Logger.getLogger(DriverFactory.class);

    public static WebDriver getDriver() {
        String browser = ConfigReader.get("browser");
        log.info("Requested browser: " + browser);

        if (browser != null && browser.equalsIgnoreCase("chrome")) {
            log.info("Launching Chrome browser");
            driver = new ChromeDriver();
        } else {
            log.error("Unsupported or missing browser type in config.properties");
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            log.info("Closing browser");
            driver.quit();
        }
    }
}
