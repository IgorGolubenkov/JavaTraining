package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.addressbook.model.ContactData;

import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(ChromeDriver wd) {
        super(wd);
    }
    public void fillGroupForm(ContactData contactData) {
        type(By.xpath("//input[@name='firstname']"), contactData.getFirstname());
        type(By.xpath("//input[@name='middlename']"), contactData.getMiddlename());
        type(By.xpath("//input[@name='lastname']"), contactData.getLastname());
        type(By.xpath("//input[@name='nickname']"), contactData.getNickname());
        type(By.xpath("//input[@name='title']"), contactData.getTitle());
        type(By.xpath("//input[@name='company']"), contactData.getCompany());
        type(By.xpath("//textarea[@name='address']"), contactData.getAddress());
        type(By.xpath("//input[@name='homepage']"), contactData.getHomepage());
    }
    public void submitContactCreation() {
        clickSearch(By.xpath(".//*[@id='content']/form/input[21]"));
    }
    public void deleteSelectedContact() {
        List<WebElement> contact = wd.findElementsByXPath("//input[@name='selected[]']");
        WebElement locator = contact.get(0);
        clickLocator(locator);
    }
    public void submitContactDeletion() {
        clickSearch(By.xpath("//input[@value='Delete']"));
    }
    public void confirmationContactDeletion() {
        Alert alert = alert();
        alert.accept();
    }
    public void modificationSelectedContact() {
        List<WebElement> contact = wd.findElementsByXPath("//img[@title='Edit']");
        WebElement locator = contact.get(0);
        clickLocator(locator);
    }
    public void submitContactModification() {
        clickSearch(By.xpath(".//*[@id='content']/form[1]/input[1]"));
    }
}
