package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{
    private final ApplicationManager app;

    private Contacts contactCashe = null;

    public ContactHelper(ApplicationManager app) {
        super(app.wd);
        this.app = app;
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        if (creation) {
            try {
                new Select(wd.findElement(By.xpath("//select[@name='new_group']"))).selectByVisibleText(contactData.getGroup());
            } catch (Exception exc) {
                GroupData groupData = new GroupData().withName("test group");
                System.out.println(exc);
                initCreationGroup(groupData);
                app.goTo().goToHomePage();
                app.goTo().goToAddandEditContactPage();
            }
        } else {
            Assert.assertFalse(isElementPresent(By.xpath("//select[@name='new_group']")));
        }
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

    public void submitContactDeletion() {
        clickSearch(By.xpath("//input[@value='Delete']"));
    }

    public void confirmationContactDeletion() {
        Alert alert = alert();
        alert.accept();
    }

    public void submitContactModification() {
        clickSearch(By.xpath(".//*[@id='content']/form[1]/input[1]"));
    }

    public void createContact(ContactData contact) throws InterruptedException {
        app.goTo().goToAddandEditContactPage();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCashe = null;
        app.goTo().goToHomePage();
        wd.navigate().refresh();
        Thread.sleep(2000);
    }

    public void initCreationGroup(GroupData group) {
        app.goTo().groupPage();
        app.group().create(group);
        app.goTo().goToHomePage();
    }

    public boolean thereIsAGroupForChoice() {
        return isElementPresent(By.xpath("//select[@name='to_group']/option"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//input[@name='selected[]']"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elemContacts = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement elemContact : elemContacts ) {
            String firstName = elemContact.findElements(By.tagName("td")).get(2).getText();
            String lastName = elemContact.findElements(By.tagName("td")).get(1).getText();
            String address = elemContact.findElements(By.tagName("td")).get(3).getText();
            int id = Integer.parseInt(elemContact.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName).withAddress(address);
            contacts.add(contact);
        }
        return contacts;
    }

    public Contacts all() {
        if (contactCashe != null) {
            return new Contacts(contactCashe);
        }
        contactCashe = new Contacts();
        List<WebElement> elemContacts = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement elemContact : elemContacts ) {
            String firstName = elemContact.findElements(By.tagName("td")).get(2).getText();
            String lastName = elemContact.findElements(By.tagName("td")).get(1).getText();
            String address = elemContact.findElements(By.tagName("td")).get(3).getText();
            int id = Integer.parseInt(elemContact.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName).withAddress(address);
            contactCashe.add(contact);
        }
        return contactCashe;
    }


    public void modificationSelectedContact(int index) {
        wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();   //wd.findElementsByXPath("//img[@title='Edit']");
    }


    public void selectedToById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }

    public void modify(ContactData contact) {
        selectedToById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCashe = null;
        app.goTo().goToHomePage();
    }

    public void deleteSelectedContact(int index) {
        wd.findElements(By.xpath("//input[@name='selected[]']")).get(index).click();  //wd.findElementsByXPath("//input[@name='selected[]']");
    }

    private void selectedForDeletedToById(int id) {
        wd.findElement(By.xpath("//input[@id='" + id + "']")).click();
    }

    public void delete(ContactData contact) {
        selectedForDeletedToById(contact.getId());
        submitContactDeletion();
        confirmationContactDeletion();
        contactCashe = null;
        app.goTo().goToHomePage();
    }
}
