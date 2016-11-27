package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;

    private SessionHelper sessionHelper;
    private Navigationhelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private String browser;
    private DbHelper bdHelper;

    public ApplicationManager(String browser)  {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        bdHelper = new DbHelper();

        String DriverPath = "src/ExternalJars";
        if ("".equals(properties.getProperty("selenium.server"))) {
            if (browser.equals(BrowserType.CHROME)) {
                System.setProperty("webdriver.chrome.driver",
                        DriverPath + "/chromedriver_win/chromedriver.exe");
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.EDGE)) {
                System.setProperty("webdriver.edge.driver",
                        DriverPath + "/edgedriver/MicrosoftWebDriver.exe");
                wd = new EdgeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                System.setProperty("webdriver.ie.driver",
                        DriverPath + "/iedriver/IEDriverServer.exe");
                wd = new InternetExplorerDriver();
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }
        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wd.manage().getCookies();
        wd.get(properties.getProperty("web.besUrl"));

        navigationHelper = new Navigationhelper(this);
        sessionHelper = new SessionHelper(wd);
        groupHelper = new GroupHelper(this);
        contactHelper = new ContactHelper(this);

        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }
    public void stop() {
        wd.quit();
    }
    public GroupHelper group() {
        return groupHelper;
    }
    public Navigationhelper goTo() {
        return navigationHelper;
    }
    public ContactHelper contact() {
        return contactHelper;
    }
    public DbHelper db() {
        return bdHelper;
    }
}
