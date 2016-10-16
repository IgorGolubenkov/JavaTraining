package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreationContact extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().goToHomePage();
        if (! app.contact().thereIsAGroupForChoice()) {
            app.contact().initCreationGroup(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test(enabled = true)
    public void testCreationContact() throws InterruptedException {
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/foto.jpg");
        ContactData contact = new ContactData().withFirstname("test1").withMiddlename("test2").withLastname("test3").withNickname("test4")
                .withTitle("test4").withCompany("test5").withAddress("test6").withHomepage("test7").withGroup("test254").withPhoto(photo);
        app.contact().createContact(contact, true);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        //contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
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
