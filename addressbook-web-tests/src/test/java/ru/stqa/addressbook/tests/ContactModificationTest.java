package ru.stqa.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase{

    @DataProvider
    public Iterator<Object[]> modifyContactsFromJSON() throws IOException {
        try (BufferedReader readear = new BufferedReader(new FileReader
                (new File("src/test/resources/TestData/contacts/contacts_modify.json")))) {
            String json = "";
            String line = readear.readLine();
            while (line != null) {
                json += line;
                line = readear.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        app.goTo().goToHomePage();
        if (! app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstname("test1").withMiddlename("test2").withLastname("test3")
                    .withNickname("test4").withTitle("test4").withCompany("test5").withAddress("test6")
                    .withHomepage("test7"), true);  //.withGroup("test group")
        }
    }

    @Test(dataProvider = "modifyContactsFromJSON")
    public void testContactModification(ContactData contact) throws InterruptedException {

        Contacts before = app.db().contacts();
        ContactData modifyConctacts = before.iterator().next();
        ContactData contactData = contact.withId(modifyConctacts.getId());
        app.contact().modify(contactData);
        assertEquals(app.contact().count(), before.size()); // UI hash
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(modifyConctacts).withAdded(contactData.removePhoto())));
    }
}
