package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.addressbook.model.ContactData;

public class ContactHelper {

    /**FirefoxDriver wd;*/
    private ChromeDriver wd;

    public ContactHelper(ChromeDriver wd) {
        this.wd = wd;
    }
    public void fillGroupForm(ContactData contactData) {
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
        wd.findElement(By.xpath(".//*[@id='content']/form/input[21]")).click();
    }
}
