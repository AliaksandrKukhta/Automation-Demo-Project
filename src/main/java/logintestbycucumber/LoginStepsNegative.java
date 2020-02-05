package logintestbycucumber;

import browser.DriverType;
import browser.drivemanagers.DriverManager;
import browser.drivemanagers.DriverManagerFactory;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import mail.Mail;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginStepsNegative {
    protected static final String MAIN_URL = "http://mail.ru";
    protected WebDriver webDriver;
    protected Mail loginPage;
    DriverManager driverManager;

    public LoginStepsNegative() {
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        webDriver = driverManager.getDriver();
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        loginPage = new Mail(webDriver);
    }

    @Given("^I am on main URL$")
    public void loadMainPage() {
        webDriver.get(MAIN_URL);
    }


    @When("^I login as user with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void loginAsUserWithCredentials(String name, String password) {
        loginPage.enterLogin(name);
        loginPage.enterPassword(password);
    }

    @Then("^I see error message$")
    public void seeErrorMessage() throws InterruptedException {
        Thread.sleep(10000);
        Assert.assertTrue(loginPage.logoutLinkErrorPresents());
    }

    @After("@loginfeature")
    public void afterClass() {
        webDriver.quit();
    }
}
