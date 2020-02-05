package apitest;

import api.WallSend;
import browser.DriverType;
import browser.drivemanagers.DriverManager;
import browser.drivemanagers.DriverManagerFactory;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import regression.MailTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class WallSendTest {
    private WallSend wallSend;
    WebDriver driver;
    DriverManager driverManager;
    EyesRunner runner = new ClassicRunner();
    Eyes eyes = new Eyes(runner);

    @BeforeClass
    public WebDriver beforeClass() {
        eyes.setApiKey("******************************");
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://www.vk.com");
        eyes.open(driver, "www.VK.com", "Send");
        eyes.setMatchLevel(MatchLevel.LAYOUT);
        eyes.checkWindow();
        wallSend = new WallSend(driver);
        wallSend.apiLogin("*******");
        wallSend.apiPassword("*********");
        wallSend.apiButton();
        wallSend.mainPage();
        eyes.checkWindow();
        return driver;
    }

    @Test
    public void testSendMessage() throws IOException, URISyntaxException, InterruptedException {
        Thread.sleep(5000);
        WebElement post = driver.findElement(By.xpath(".//div[contains(@data-post-id, "
                + wallSend.sendMessage() + ")]"));
        Assert.assertTrue(post.isDisplayed());
    }

    @AfterMethod
    public void afterMethod() {
        eyes.closeAsync();
        driver.quit();
        eyes.abortIfNotClosed();
        driverManager.quitDriver();
    }
}