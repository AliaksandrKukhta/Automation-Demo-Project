package api;

import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.URISyntaxException;

public class WallSend extends Wall {
    @FindBy(xpath = ".//input[@placeholder=\"Телефон или email\"]")
    WebElement emailField;
    @FindBy(xpath = ".//input[@placeholder=\"Пароль\"]")
    WebElement passwordField;
    @FindBy(xpath = ".//button[@id=\"index_login_button\"]")
    WebElement enterApiButton;
    @FindBy(xpath = ".//span[text()=\"Моя страница\"]")
    WebElement myPage;
    @FindBy(xpath = ".//div[contains(@data-post-id, ")
    WebElement post;

    public WallSend(WebDriver webdriver) {
        PageFactory.initElements(webdriver, this);
    }

    public void apiLogin(String login) {
        emailField.clear();
        emailField.sendKeys(login);
    }

    public void apiPassword(String password) {
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void apiButton() {
        enterApiButton.click();
    }

    public void mainPage() {
        myPage.click();
    }

    public String sendMessage() throws URISyntaxException, IOException {
        String text = "Hello, Automation";
        URIBuilder builder = GetUriBuilder("post", ACCESS_TOKEN, OWNER_ID, V);
        builder.setParameter("message", text);
        HttpResponse response = getResponse(builder);
        String idPost = EntityUtils.toString(response.getEntity()).substring(23, 27);
        System.out.println("send " + idPost);
        return idPost;
    }
}
