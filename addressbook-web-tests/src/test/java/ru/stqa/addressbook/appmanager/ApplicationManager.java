package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    /**FirefoxDriver wd;*/
    ChromeDriver wd;

    private SessionHelper sessionHelper;
    private Navigationhelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;

    public void init() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\Training Java\\JavaTraining\\addressbook-web-tests\\src\\ExternalJars\\chromedriver_win\\chromedriver.exe" );
        wd = new ChromeDriver();
        /**wd = new FirefoxDriver();*/
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        sessionHelper = new SessionHelper(wd);
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new Navigationhelper(wd);
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
