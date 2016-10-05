package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd;

    private SessionHelper sessionHelper;
    private Navigationhelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }
    public void init() {

        String DriverPath = "src/ExternalJars";
        // рабочая директория "C:/AutomationTesting/JavaTraining/addressbook-web-tests/src/ExternalJars"
        // домашная директория "D:/Training Java/JavaTraining/addressbook-web-tests/src/ExternalJars"
        if (Objects.equals(browser, BrowserType.CHROME)) {
            System.setProperty("webdriver.chrome.driver",
                    DriverPath + "/chromedriver_win/chromedriver.exe");
            wd = new ChromeDriver();
        } else if (Objects.equals(browser, BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (Objects.equals(browser, BrowserType.EDGE)) {
            System.setProperty("webdriver.edge.driver",
                    DriverPath + "/edgedriver/MicrosoftWebDriver.exe");
            wd = new EdgeDriver();
        } else if (Objects.equals(browser, BrowserType.IE)) {
            System.setProperty("webdriver.ie.driver",
                    DriverPath + "/iedriver/IEDriverServer.exe");
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        navigationHelper = new Navigationhelper(this);
        sessionHelper = new SessionHelper(wd);
        groupHelper = new GroupHelper(this);
        contactHelper = new ContactHelper(this);
        sessionHelper.login("admin", "secret");
    }
    public void stop() {
        wd.quit();
    }
    public GroupHelper getGroupHelper() {
        return groupHelper;
    }
    public Navigationhelper getNavigationHelper() {
        return navigationHelper;
    }
    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
