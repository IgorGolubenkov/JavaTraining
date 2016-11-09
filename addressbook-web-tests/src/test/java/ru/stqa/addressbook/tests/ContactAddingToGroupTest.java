package ru.stqa.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.testng.Assert.assertTrue;

public class ContactAddingToGroupTest extends TestBase {

    Logger logger = LoggerFactory.getLogger(ContactAddingToGroupTest.class);

    private ContactData ensureContact;
    private GroupData ensureGroup;

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException, IOException {
        try (BufferedReader redear = new BufferedReader(new FileReader
                (new File("src/test/resources/TestData/contacts/contact_ensure.json")))) {
            String json = "";
            String line = redear.readLine();
            while (line != null) {
                json += line;
                line = redear.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            ensureContact = contacts.iterator().next();
        } catch (Exception exc) {
            logger.error(String.format("Чтение и Дисерелизация тестовых данных упало с ошибкой: %s", exc));
        }
        if (ensureContact.getGroups().size() > 0) {
            assertTrue(ensureContact.getGroups().size() == 1);
        }
        ensureGroup = ensureContact.getGroups().iterator().next();
        Contacts contacts = app.db().contacts();
        if (!contacts.stream().anyMatch
                (contactData -> Objects.equals(contactData.getFirstname(), ensureContact.getFirstname()))) {
            app.goTo().goToHomePage();
            app.contact().createContact(ensureContact, false);
            contacts = app.db().contacts();
        }
        ensureContact.withId(contacts.stream()
                .filter((contactData) -> Objects.equals(contactData.getFirstname(), ensureContact.getFirstname())).findFirst()
                .get().getId());
        Groups groups = app.db().groups();
        if (!groups.stream().anyMatch
                (groupData -> Objects.equals(groupData.getName(), ensureGroup.getName()))) {
            app.goTo().groupPage();
            app.group().create(ensureGroup);
            app.goTo().goToHomePage();
        }
        if (contacts.stream().anyMatch(contactData -> contactData.getGroups()
                .stream().anyMatch(groupData -> Objects.equals(groupData.getName(), ensureGroup.getName())))){
            app.contact().removeContactFromGroup(ensureContact, ensureGroup);
            app.contact().returnAllContactsPage();
        }
    }

    @Test
    public void testContactAddingToGroup() {
        app.contact().addingContactInGroup(ensureContact, ensureGroup);
        Contacts contacts = app.db().contacts();
        assertTrue(contacts.stream().filter(contactData -> contactData.getId() == ensureContact.getId())
                .findFirst().get().getGroups().stream()
                .anyMatch(groupData -> groupData.getName().equals(ensureGroup.getName())));
    }
}
