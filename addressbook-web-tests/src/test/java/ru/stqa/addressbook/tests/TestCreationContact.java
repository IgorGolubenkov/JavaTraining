package ru.stqa.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreationContact extends TestBase{

    @DataProvider
    public Iterator<Object[]> validContactsFromCSV() throws IOException {
        try (BufferedReader redear = new BufferedReader(new FileReader
                (new File("src/test/resources/TestData/contacts/contacts.csv")))) {
            List<Object[]> list = new ArrayList<Object[]>();
            String line = redear.readLine();
            while (line != null) {
                String[] split = line.split(";");
                list.add(new Object[] {new ContactData().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])});
                line = redear.readLine();
            }
            return list.iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromXML() throws IOException {
        BufferedReader redear = new BufferedReader(new FileReader
                (new File("src/test/resources/TestData/contacts/contacts.xml")));
        String xml = "";
        String line = redear.readLine();
        while (line != null) {
            xml += line;
            line = redear.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        List<ContactData> groups = (List<ContactData>) xstream.fromXML(xml);
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJSON() throws IOException {
        try (BufferedReader redear = new BufferedReader(new FileReader
                (new File("src/test/resources/TestData/contacts/contacts.json")))) {
            String json = "";
            String line = redear.readLine();
            while (line != null) {
                json += line;
                line = redear.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> groups = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().goToHomePage();
        if (! app.contact().thereIsAGroupForChoice()) {
            app.contact().initCreationGroup(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test(dataProvider = "validContactsFromJSON")
    public void testCreationContact(ContactData contact) throws InterruptedException {
        Contacts before = app.db().contacts();
        File photo = new File("src/test/resources/foto.jpg");
        app.contact().createContact(contact.withPhoto(photo), true);
        Contacts after = app.db().contacts();

        Assert.assertEquals(app.contact().count(), before.size() + 1);

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream()
                .mapToInt((c) -> c.getId()).max().getAsInt()).removePhoto())));


//        assertThat(after, equalTo(before.withAdded(contact     сравнение с формой редактирвоания
//                .withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt())
//                .withAllPhone(app.contact().mergePhones(contact))
//                .removeHomePhone().removeMobilePhone().removeWorkPhone().removePhoto())));
        }

    @Test(enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/foto.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}
