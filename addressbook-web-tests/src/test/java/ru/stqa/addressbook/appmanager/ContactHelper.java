package ru.stqa.addressbook.appmanager;

import org.aspectj.weaver.ast.Not;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{
    private final ApplicationManager app;

    public ContactHelper(ApplicationManager app) {
        super(app.wd);
        this.app = app;
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.xpath("//input[@name='firstname']"), contactData.getFirstname());
        type(By.xpath("//input[@name='middlename']"), contactData.getMiddlename());
        type(By.xpath("//input[@name='lastname']"), contactData.getLastname());
        type(By.xpath("//input[@name='nickname']"), contactData.getNickname());
        type(By.xpath("//input[@name='title']"), contactData.getTitle());
        type(By.xpath("//input[@name='company']"), contactData.getCompany());
        type(By.xpath("//textarea[@name='address']"), contactData.getAddress());
        type(By.xpath("//input[@name='homepage']"), contactData.getHomepage());

        if (creation) {
            new Select(wd.findElement(By.xpath("//select[@name='new_group']"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.xpath("//select[@name='new_group']")));
        }
    }

    public void submitContactCreation() {
        clickSearch(By.xpath(".//*[@id='content']/form/input[21]"));
    }

    public void deleteSelectedContact(int index) {
        wd.findElements(By.xpath("//input[@name='selected[]']")).get(index).click();  //wd.findElementsByXPath("//input[@name='selected[]']");
    }

    public void submitContactDeletion() {
        clickSearch(By.xpath("//input[@value='Delete']"));
    }

    public void confirmationContactDeletion() {
        Alert alert = alert();
        alert.accept();
    }

    public void modificationSelectedContact(int index) {
        wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();   //wd.findElementsByXPath("//img[@title='Edit']");
    }

    public void submitContactModification() {
        clickSearch(By.xpath(".//*[@id='content']/form[1]/input[1]"));
    }

    public void createContact(ContactData contactData, boolean creation) {
        if (! thereIsAGroupForChoice()) {
            initCreationGroup(new GroupData("test1", "test2", "test3"));
        }
        app.getNavigationHelper().goToAddandEditContactPage();
        fillContactForm(contactData, creation);
        submitContactCreation();
        app.getNavigationHelper().goToHomePage();
    }

    public void initCreationGroup(GroupData group) {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().createGroup(group);
        app.getNavigationHelper().goToHomePage();
    }

    public boolean thereIsAGroupForChoice() {
        return isElementPresent(By.xpath("//select[@name='to_group']/option"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//input[@name='selected[]']"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elementsLastName = wd.findElements(By.xpath("//tr[@name='entry']//td[2]"));
        List<WebElement> elementsFirstName = wd.findElements(By.xpath("//tr[@name='entry']//td[3]"));
        for (WebElement elementLastName : elementsLastName ) {
            String firstName = elementLastName.getText();
            String lastName = new String();
            for (WebElement elementFirstName : elementsFirstName) {
                lastName = elementFirstName.getText();
            }
            ContactData contact = new ContactData(firstName, null, lastName, null, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }

}
