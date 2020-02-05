package apitest;

import api.WallDelete;
import api.WallEdit;
import api.WallSend;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class WallDeleteTest {
    WallSendTest wallSendTest = new WallSendTest();
    WallSend wallSend;
    WallDelete wallDelete;
    WallEdit wallEdit;
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = wallSendTest.beforeClass();
        wallSend = new WallSend(driver);
        wallDelete = new WallDelete();
        wallEdit = new WallEdit();
    }

    @Test
    public void testDeleteMessage() throws IOException, URISyntaxException {
        WebElement post = driver.findElement(By.xpath(".//div[contains(@data-post-id, " +
                wallDelete.deleteMessage(wallEdit.editMessage(wallSend.sendMessage())) + ")]"));
        Assert.assertTrue(post.isDisplayed());
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}