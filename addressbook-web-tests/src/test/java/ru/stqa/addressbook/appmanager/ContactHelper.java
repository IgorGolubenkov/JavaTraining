package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        attach(By.xpath("//input[@name='photo']"), contactData.getPhoto());
        type(By.xpath("//input[@name='home']"), contactData.getHomePhone());
        type(By.xpath("//input[@name='mobile']"), contactData.getMobilePhone());
        type(By.xpath("//input[@name='work']"), contactData.getWorkPhone());
        type(By.xpath("//input[@name='title']"), contactData.getTitle());
        type(By.xpath("//input[@name='company']"), contactData.getCompany());
        type(By.xpath("//textarea[@name='address']"), contactData.getAddress());
        type(By.xpath("//input[@name='homepage']"), contactData.getHomepage());
        type(By.xpath("//input[@name='email']"), contactData.getEmail());
        type(By.xpath("//input[@name='email2']"), contactData.getEmail2());
        type(By.xpath("//input[@name='email3']"), contactData.getEmail3());
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

    public void createContact(ContactData contact, boolean addInGroups) throws InterruptedException {
        app.goTo().goToAddandEditContactPage();
        fillContactForm(contact, addInGroups);
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

    public Contacts all() throws InterruptedException {
        if (contactCashe != null) {
            return new Contacts(contactCashe);
        }
        contactCashe = new Contacts();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement row : rows ) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String address = cells.get(3).getText();
            String[] phones = cells.get(5).getText().split("\n");
            String allPhones = cells.get(5).getText();
            String email1 = null;
            String email2 = null;
            String email3 = null;
            try {
                List<WebElement> allEmail = cells.get(4).findElements(By.tagName("a"));
                email1 = allEmail.get(0).getText();
                email2 = allEmail.get(1).getText();
                email3 = allEmail.get(2).getText();
            } catch (IndexOutOfBoundsException | NullPointerException exc) {
                System.out.println(exc);
            }
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
                    .withAllPhone(allPhones).withEmail(email1)
                    .withEmail2(email2).withEmail3(email3).withAddress(address);
            contactCashe.add(contact);
        }
        return contactCashe;
    }

    public void modificationSelectedContact(int index) {
        wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();   //wd.findElementsByXPath("//img[@title='Edit']");
    }

    public void selectedToById(int id) {
        wd.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", id))).click();
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
        wd.findElement(By.cssSelector(String.format("input[id='%s']", id))).click();
    }

    public void delete(ContactData contact) {
        selectedForDeletedToById(contact.getId());
        submitContactDeletion();
        confirmationContactDeletion();
        contactCashe = null;
        app.goTo().goToHomePage();
    }

    private void initContactModifycationById(int id) {
        //WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[id='%s']", id)));
        //WebElement row = checkbox.findElement(By.xpath("./../.."));
        //List<WebElement> cells = row.findElements(By.tagName("td"));
        //cells.get(7).findElement(By.tagName("a")).click();

        wd.findElement(By.xpath(String.format("//input[@id='%s']/../../td[8]/a", id))).click();
        //wd.findElement(By.xpath(String.format("//tr[.//input[@id='%s']]/td[8]/a", id))).click();
    }

    public ContactData infoFromEditFromPhone(ContactData contact) {
        initContactModifycationById(contact.getId());
        String firstName = wd.findElement(By.cssSelector("input[name='firstname']")).getAttribute("value");
        String lastName = wd.findElement(By.cssSelector("input[name='lastname']")).getAttribute("value");
        String homePhone = wd.findElement(By.cssSelector("input[name='home']")).getAttribute("value");
        String mobilePhone = wd.findElement(By.cssSelector("input[name='mobile']")).getAttribute("value");
        String workPhone = wd.findElement(By.cssSelector("input[name='work']")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstName).withLastname(lastName)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone);
    }

    public ContactData infoFromEditFrom(ContactData contact) {
        initContactModifycationById(contact.getId());
        String firstName = wd.findElement(By.cssSelector("input[name='firstname']")).getAttribute("value");
        String lastName = wd.findElement(By.cssSelector("input[name='lastname']")).getAttribute("value");
        String homePhone = wd.findElement(By.cssSelector("input[name='home']")).getAttribute("value");
        String mobilePhone = wd.findElement(By.cssSelector("input[name='mobile']")).getAttribute("value");
        String workPhone = wd.findElement(By.cssSelector("input[name='work']")).getAttribute("value");
        String email = wd.findElement(By.cssSelector("input[name='email']")).getAttribute("value");
        String email2 = wd.findElement(By.cssSelector("input[name='email2']")).getAttribute("value");
        String email3 = wd.findElement(By.cssSelector("input[name='email3']")).getAttribute("value");
        String address = wd.findElement(By.cssSelector("textarea[name='address']")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstName).withLastname(lastName)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).withEmail(email)
                .withEmail2(email2).withEmail3(email3).withAddress(address);
    }

    public ContactData infoFromDetailPage(ContactData contact) {
        openDetailInfoPageContactById(contact.getId());
        String allInfo = wd.findElement(By.cssSelector("div[id='content']")).getText();
        String[] allInformation = allInfo.split("\n");
        String[] fullName = allInformation[0].split("\\s");
        String firstName = fullName[0];
        String lastName = null;
        if (fullName.length == 3) {
            lastName = fullName[2];
        } else if (fullName.length == 2) {
            lastName = fullName[1];
        }
        String address = allInformation[4];
        String email = allInformation[11].replaceAll("(\\s{1}[(]w*.*$)", "");
        String email2 = allInformation[12].replaceAll("(\\s{1}[(]w*.*$)", "");
        String email3 = allInformation[13].replaceAll("(\\s{1}[(]w*.*$)", "");
        String homePhone = allInformation[6].replaceAll("(^H: )", "");
        String mobilePhone = allInformation[7].replaceAll("(^M: )", "");
        String workPhone = allInformation[8].replaceAll("(^W: )", "");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstName).withLastname(lastName)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).withEmail(email)
                .withEmail2(email2).withEmail3(email3).withAddress(address);
    }

    private void openDetailInfoPageContactById(int id) {
        wd.findElement(By.xpath(String.format(String.format("//a[@href='view.php?id=%s']", id)))).click();
    }

    public static String cleanedPhone(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    public String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .filter((s -> ! s.equals(""))).map(ContactHelper::cleanedPhone).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String email) {
        return email.replaceAll("(^\\s*)|(\\s*$)", "");
    }
    public String mergeEmailEditForm(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .filter((s -> ! s.equals(""))).map(ContactHelper::cleaned).collect(Collectors.joining("\n"));
    }

    public int count() {
        return wd.findElements(By.cssSelector("tr[name='entry']")).size();
    }
}
