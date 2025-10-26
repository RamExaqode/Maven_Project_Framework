package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class ConfigReader {
    private static Properties prop = new Properties();
    static Logger log = Logger.getLogger(ConfigReader.class);

    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            prop.load(fis);
            log.info("Loaded config.properties successfully");
        } catch (IOException e) {
            log.error("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        String value = prop.getProperty(key);
        log.debug("Fetching config key: " + key + " → " + value);
        return value;
    }
}
