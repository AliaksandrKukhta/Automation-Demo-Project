package mail;

import data.DataBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

public class Mail extends DataBase {
    private static final int LINK_PRESENSE_TIMEOUT = 10;
    private WebDriver driver;
    private String locationJPEGFile = "D:\\Java\\курсы Automation\\HW_PVT\\FinalProject\\ILoveAutomation.jpg";
    private String locationDOCXFile = "D:\\Java\\курсы Automation\\HW_PVT\\FinalProject\\saveInDraft.docx";
    @FindBy(name = "login")
    private WebElement loginField;
    @FindBy(name = "password")
    private WebElement emailField;
    @FindBy(xpath = ".//input[@value='Ввести пароль']")
    private WebElement submit;
    @FindBy(xpath = ".//input[@value=\"Ввести пароль\"]")
    private WebElement buttonEnter;
    @FindBy(xpath = ".//a[@title=\"выход\"]")
    private WebElement logoutLink;
    @FindBy(xpath = "(.//div[@class=\"ll-av__container\"])[1]")
    private WebElement selectLetter;
    @FindBy(xpath = ".//span[text()='Спам']")
    private WebElement buttonSpamMessage;
    @FindBy(xpath = ".//span[text()='Перемещено в спам']")
    private WebElement spamMessage;
    @FindBy(xpath = ".//a[@title=\"Спам\"]")
    private WebElement buttonSpam;
    @FindBy(xpath = "//a[@href=\"/inbox/\"]")
    private WebElement inboxFolder;
    @FindBy(xpath = "(.//span[text()='Не спам'])[1]")
    private WebElement noSpamButton;
    @FindBy(xpath = ".//span[contains (text(),'Перемещено в папку ')]")
    private WebElement noSpamMessage;
    @FindBy(xpath = ".//span[@title=\"Написать письмо\"]")
    private WebElement buttonNewMessage;
    @FindBy(xpath = "(//input[@class=\"container--H9L5q size_s--3_M-_\"])[1]")
    private WebElement whom;
    @FindBy(xpath = "(.//div[@class=\"b-userpic b-userpic_small\"])[1]")
    private WebElement firstAdress;
    @FindBy(xpath = "(.//div[@class=\"b-userpic b-userpic_small\"])[2]")
    private WebElement secondAdress;
    @FindBy(xpath = ".//span[text()='Отправить']")
    private WebElement sendButton;
    @FindBy(xpath = ".//div[@role=\"textbox\"]")
    private WebElement addText;
    @FindBy(xpath = ".//input[@class=\"desktopInput--3cWPE\"]")
    private WebElement addSomeFileButton;
    @FindBy(xpath = ".//span[@title=\"Сохранить\"]")
    private WebElement saveButton;
    @FindBy(xpath = ".//a[@class='layer__link']")
    private WebElement sendMessage;
    @FindBy(xpath = ".//span[contains(*, 'Сохранено в черновиках')]")
    private WebElement saveSendMessage;
    @FindBy(xpath = ".//span[text()='В папку']")
    private WebElement inFolderButton;
    @FindBy(xpath = "(.//div[text()='Социальные сети'])[1]")
    private WebElement socialNetworkButton;
    @FindBy(xpath = ".//span[contains(*, 'Перемещено в папку «Социальные сети»')]")
    private WebElement intoSocialNetworksMessage;
    @FindBy(xpath = ".//div[@id=\"mailbox:error\"]")
    private WebElement logoutLinkError;

    @FindBy(xpath = "(.//span[@class=\"button2__txt\"])[4]")
    private WebElement newFolderButton;
    @FindBy(xpath = ".//input[@name=\"name\"]")
    private WebElement newFolderName;
    @FindBy(xpath = ".//span[contains(text(),\"Добавить папку\")]")
    private WebElement addNewFolderButton;
    @FindBy(xpath = ".//div[contains(text(),'')]")
    private WebElement folder;

    @FindBy(xpath = ".//i[text()=\"Все проекты\"]")
    private WebElement allProjects;
    @FindBy(xpath = ".//a[text()=\"Гороскопы\"]")
    private WebElement horoscope;
    @FindBy(xpath = ".//span[text()=\"Телец\"]")
    private WebElement taurus;
    @FindBy(xpath = ".//h1[text()=\"Гороскоп на сегодня: Телец\"]")
    private WebElement horoMessage;

    @FindBy(xpath = "(.//a[text()=\"Авто\"])[1]")
    private WebElement auto;
    @FindBy(xpath = "(.//span[contains(text(), \"Новости\")])[1]")
    private WebElement news;
    @FindBy(xpath = ".//img[@title=\"Авто Mail.ru\"]")
    private WebElement autoLogo;
    @FindBy(xpath = "( .//span[text()=\"Новости\"])[3]")
    private WebElement newsLogo;

    @FindBy(xpath = ".//a[text()=\"Список всех проектов\"]")
    private WebElement listOfAllProjects;
    @FindBy(xpath = ".//span[text()=\"Здоровье\"]")
    private WebElement health;
    @FindBy(xpath = ".//img[@title=\"Здоровье Mail.ru\"]")
    private WebElement healthLogo;

    public boolean health() {
        allProjects.click();
        listOfAllProjects.click();
        health.click();
        return healthLogo.isDisplayed();
    }

    public boolean autoLogo() {
        allProjects.click();
        auto.click();
        news.click();
        return autoLogo.isDisplayed();
    }

    public boolean autoNews() {
        return newsLogo.isDisplayed();
    }

    public boolean taurusHoroscopeToday() {
        allProjects.click();
        horoscope.click();
        taurus.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(horoMessage);
        actions.perform();
        return horoMessage.isDisplayed();
    }

    public boolean sendMessageLink() {
        return sendMessage.isDisplayed();
    }

    public boolean saveSendMessageLink() {
        return saveSendMessage.isDisplayed();
    }

    public boolean saveMessageLinkIntoSocialNetwoks() {
        return intoSocialNetworksMessage.isDisplayed();
    }

    public void goInFolderButton() {
        inFolderButton.click();
    }

    public void goToSocialNetworks() {
        socialNetworkButton.click();
    }

    public void goToSpam() {
        buttonSpam.click();
    }

    public boolean outOfSpamMessage() {
        return noSpamMessage.isDisplayed();
    }

    public void extractFromSpam() {
        noSpamButton.click();
    }

    public void selectFirstLetter() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(selectLetter));
        executor.executeScript("arguments[0].click()", selectLetter);
    }

    public void goToInboxFolder() {
        inboxFolder.click();
    }

    public void createNewMessage() {
        buttonNewMessage.click();
        addText.sendKeys("Hello");
    }

    public void saveLetter() {
        saveButton.click();
    }

    public void addPicture() {
        addSomeFileButton.sendKeys(locationJPEGFile);
    }

    public void addDOCXFile() {
        addSomeFileButton.sendKeys(locationDOCXFile);
    }

    public void setDestination(String... e_mail) {
        whom.click();
        whom.sendKeys(e_mail);
    }

    public void clickSendButton() {
        sendButton.click();
    }

    public Mail(WebDriver webdriver) {
        PageFactory.initElements(webdriver, this);
        this.driver = webdriver;
    }

    public void enterLogin(String login) {
        loginField.clear();
        loginField.sendKeys(login);
        loginField.submit();
    }

    public void enterPass(String password) {
        emailField.sendKeys(password);
    }

    public void clickEnterButton() {
        buttonEnter.click();
    }

    public void enterPassword(String password) {
        emailField.clear();
        emailField.sendKeys(password);
        buttonEnter.click();
    }

    public boolean logoutLinkPresents() {
        return logoutLink.isDisplayed();
    }

    public boolean logoutLinkErrorPresents() {
        return logoutLinkError.isDisplayed();
    }

    public boolean letterIsSent() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(logoutLink));
        return logoutLink.isDisplayed();
    }
}