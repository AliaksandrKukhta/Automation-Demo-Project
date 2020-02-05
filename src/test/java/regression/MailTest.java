package regression;

import data.DataBase;
import browser.DriverType;
import browser.configuration.Configuration;
import browser.drivemanagers.DriverManager;
import browser.drivemanagers.DriverManagerFactory;
import mail.Mail;
import model.User;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.xml.sax.SAXException;
import parsing.Parsing;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MailTest extends DataBase {
    private static final Logger logger = Logger.getLogger(MailTest.class);
    DriverManager driverManager;
    WebDriver driver;
    private Mail mail;
    User kukhta = new User();
    User pupkin = new User();
    User ivanov = new User();
    User sidorov = new User();
    User petrov = new User();
    User jarosh = new User();
    User kashirov = new User();
    User revt = new User();
    String space = " ";


    @BeforeTest
    public void beforeTest() {
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = driverManager.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, SECONDS);
        driver.get(Configuration.getMainURL());
        logger.info("Instance mail");
        mail = new Mail(driver);
        fillingInUser(kukhta);
        logger.info("Filled in user 'Kukhta' fields");
        fillingInUser(sidorov);
        logger.info("Filled in user 'Sidorov' fields");
        logger.trace("Enter Login");
        mail.enterLogin(kukhta.getLogin());
        mail.clickEnterButton();
        logger.trace("Enter Password");
        mail.enterPass(kukhta.getPassword());
        mail.clickEnterButton();
    }

    @Test
    public void sendMessageToOneAddresseeWithPicture() {
        sendMessage(kukhta.getE_mail());
        mail.addPicture();
        mail.clickSendButton();
        Assert.assertTrue(mail.sendMessageLink());
    }


    @Test
    public void moveIntoSocialNetworks() {
        mail.goToInboxFolder();
        mail.selectFirstLetter();
        mail.goInFolderButton();
        mail.goToSocialNetworks();
        Assert.assertTrue(mail.saveMessageLinkIntoSocialNetwoks());
    }

    @Test
    public void sendMessageToOneAddressee() {
        sendMessage(kukhta.getE_mail());
        mail.clickSendButton();
        Assert.assertTrue(mail.sendMessageLink());

    }

    public void sendMessage(String... email) {
        mail.createNewMessage();
        mail.setDestination(email);
        WebElement explicitWait = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[text()='Отправить']")));
    }

    @Test
    public void horoscope() {
        Assert.assertTrue(mail.taurusHoroscopeToday());
    }

    @Test
    public void news() {
        Assert.assertTrue(mail.autoLogo());
        Assert.assertTrue(mail.autoNews());
    }

    @Test
    public void health() {
        Assert.assertTrue(mail.health());
    }

    @Test
    public void sendMessageToTwoAddressees() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        Parsing parsing = new Parsing();
        parsing.parse();
        sendMessage(kukhta.getE_mail(), space, sidorov.getE_mail(), space, parsing.mails.get(0), space, parsing.mails.get(1));
        mail.clickSendButton();
        Assert.assertTrue(mail.sendMessageLink());
    }

    @Test
    public void saveMessage() {
        mail.createNewMessage();
        mail.addDOCXFile();
        mail.saveLetter();
        Assert.assertTrue(mail.saveSendMessageLink());
    }

    @Test
    public void movingFromSpam() throws InterruptedException {
        Thread.sleep(5000);
        mail.goToSpam();
        Thread.sleep(5000);
        mail.selectFirstLetter();
        mail.extractFromSpam();
        Assert.assertTrue(mail.outOfSpamMessage());
    }

    @AfterMethod
    public void afterMethod() {
        driverManager.quitDriver();
    }
}
