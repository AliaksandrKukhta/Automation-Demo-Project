package logintestbycucumber;

import browser.DriverType;
import browser.drivemanagers.DriverManager;
import browser.drivemanagers.DriverManagerFactory;
import mail.Mail;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginStepsPositive {

    protected Mail loginPage;
    protected static final String MAIN_URL = "http://mail.ru";
    private static final String LOGIN = "***********";
    private static final String PASSWORD = "**********";
    DriverManager driverManager;
    WebDriver driver;

    private static final Logger logger = Logger.getLogger(Mail.class);

    public LoginStepsPositive() {

        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        loginPage = new Mail(driver);
    }

    @Given("^I am on main application page$")
    public void loadMainPage() {
        driver.get(MAIN_URL);
    }

    @When("^I login as correct user$")
    public void loginAsCorrectUser() throws InterruptedException {
        loginPage.enterLogin(LOGIN);
        loginPage.enterPassword(PASSWORD);
        logger.trace("goto main_URL");
        Thread.sleep(1000);
    }

    @Then("^I see logout link$")
    public void seeLogoutLink() throws InterruptedException {
        Thread.sleep(10000);
        Assert.assertTrue(loginPage.letterIsSent());
    }

    @After("@login")
    public void after() {
        driverManager.quitDriver();
    }
}