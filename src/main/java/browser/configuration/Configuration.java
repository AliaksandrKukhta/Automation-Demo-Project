package browser.configuration;

import browser.DriverType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static final String RESOURCES_PATH = "src\\main\\resources\\";
    private static Properties properties;

    public static void readProperties() {
        if (properties == null) {
            properties = new Properties();
        }
        try {
            properties.load(new FileReader(new File(RESOURCES_PATH, "env.properties")));
            properties.load(new FileReader(new File(RESOURCES_PATH, "chrome.properties")));
            properties.load(new FileReader(new File(RESOURCES_PATH, "browser.properties")));
            properties.load(new FileReader(new File(RESOURCES_PATH, "firefox.properties")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static DriverType getDriverType() {
        if (properties == null) {
            readProperties();
        }
        return DriverType.valueOf(properties.getProperty("browserType"));
    }

    public static String getMainURL() {
        if (properties == null) {
            readProperties();
        }
        return (properties.getProperty("mainUrl"));
    }
}
