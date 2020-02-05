package browser.drivemanagers;

import browser.DriverType;
import browser.drivemanagers.browsermanager.ChromeDriverManager;
import browser.drivemanagers.browsermanager.GeckoDriverManager;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new GeckoDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;
    }
}