package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    static Logger log = Logger.getLogger(ScreenshotUtil.class);

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = "test-output/screenshots/" + testName + "_" + timestamp + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(screenshotPath);

        try {
            Files.createDirectories(dest.getParentFile().toPath());
            Files.copy(src.toPath(), dest.toPath());
            log.info("Screenshot captured: " + screenshotPath);
        } catch (IOException e) {
            log.error("Failed to capture screenshot", e);
        }

        return screenshotPath;
    }
}
