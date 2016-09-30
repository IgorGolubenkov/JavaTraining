package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.sql.Array;
import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
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
    public void deleteSelectedContact() {
        List<WebElement> contact = wd.findElements(By.xpath("//input[@name='selected[]']"));  //wd.findElementsByXPath("//input[@name='selected[]']");
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
        List<WebElement> contact = wd.findElements(By.xpath("//img[@title='Edit']"));   //wd.findElementsByXPath("//img[@title='Edit']");
        WebElement locator = contact.get(0);
        clickLocator(locator);
    }
    public void submitContactModification() {
        clickSearch(By.xpath(".//*[@id='content']/form[1]/input[1]"));
    }

    public void createContact(ApplicationManager app) {
        app.getNavigationHelper().goToAddandEditContactPage();
        fillContactForm(new ContactData("test1", "test2", "test3",
                "test4", "test5", "test6", "test7", "test8", "test1"), true);
        submitContactCreation();
        app.getNavigationHelper().goToHomePage();
    }

    public boolean thereIsAGroupForChoice() {
        List<WebElement> choicesGroupsList = wd.findElements(By.xpath("//select[@name='new_group']//options"));
        int lenGroupsList = choicesGroupsList.size();
        try {

        }
        if (lenGroupsList > 1) {
            for (WebElement groupFromList : choicesGroupsList) {
                String nameGroup = groupFromList.getText();
                //if (nameGroup.equals(contactData.getFirstname())) {
                return true;
            }
                } else {
            return false;
        }
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//input[@name='selected[]']"));
    }
}
