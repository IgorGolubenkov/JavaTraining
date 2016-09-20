package ru.stqa.addressbook;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class TestCreationContact {

    /**FirefoxDriver wd;*/
    ChromeDriver wd;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\Training Java\\JavaTraining\\addressbook-web-tests\\src\\ExternalJars\\chromedriver_win\\chromedriver.exe");
        wd = new ChromeDriver();
        /**wd = new FirefoxDriver();*/
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        login("admin", "secret");
        }
    private void login(String username, String password) {
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
        }
    @Test
    public void testCreationContact() {
        goToAddandEditContactPage();
        fillGroupForm(new ContactData("test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8"));
        submitContactCreation();
        }
    private void goToAddandEditContactPage() {
         wd.findElement(By.xpath("//a[contains(.,'add new')]")).click();
    }
    private void fillGroupForm(ContactData contactData) {
        wd.findElement(By.xpath("//input[@name='firstname']")).click();
        wd.findElement(By.xpath("//input[@name='firstname']")).clear();
        wd.findElement(By.xpath("//input[@name='firstname']")).sendKeys(contactData.getFirstname());
        wd.findElement(By.xpath("//input[@name='middlename']")).click();
        wd.findElement(By.xpath("//input[@name='middlename']")).clear();
        wd.findElement(By.xpath("//input[@name='middlename']")).sendKeys(contactData.getMiddlename());
        wd.findElement(By.xpath("//input[@name='lastname']")).click();
        wd.findElement(By.xpath("//input[@name='lastname']")).clear();
        wd.findElement(By.xpath("//input[@name='lastname']")).sendKeys(contactData.getLastname());
        wd.findElement(By.xpath("//input[@name='nickname']")).click();
        wd.findElement(By.xpath("//input[@name='nickname']")).clear();
        wd.findElement(By.xpath("//input[@name='nickname']")).sendKeys(contactData.getNickname());
        wd.findElement(By.xpath("//input[@name='title']")).click();
        wd.findElement(By.xpath("//input[@name='title']")).clear();
        wd.findElement(By.xpath("//input[@name='title']")).sendKeys(contactData.getTitle());
        wd.findElement(By.xpath("//input[@name='company']")).click();
        wd.findElement(By.xpath("//input[@name='company']")).clear();
        wd.findElement(By.xpath("//input[@name='company']")).sendKeys(contactData.getCompany());
        wd.findElement(By.xpath("//textarea[@name='address']")).click();
        wd.findElement(By.xpath("//textarea[@name='address']")).clear();
        wd.findElement(By.xpath("//textarea[@name='address']")).sendKeys(contactData.getAddress());
        wd.findElement(By.xpath("//input[@name='homepage']")).click();
        wd.findElement(By.xpath("//input[@name='homepage']")).clear();
        wd.findElement(By.xpath("//input[@name='homepage']")).sendKeys(contactData.getHomepage());
    }
    public void submitContactCreation() {
        wd.findElement(By.name("submit")).click();
    }
    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
