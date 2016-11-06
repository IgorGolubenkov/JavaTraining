package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;

    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;

    public ApplicationManager(String browser)  {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
//        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//        wd.get(properties.getProperty("web.besUrl"));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HTTPSession httpSession() {
        return new HTTPSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper =  new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            String DriverPath = "src/ExternalJars";
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
        }
        return wd;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }
}