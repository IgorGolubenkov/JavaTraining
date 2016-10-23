package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        app.goTo().goToHomePage();
        if (! app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstname("test1").withMiddlename("test2").withLastname("test3")
                    .withNickname("test4").withTitle("test4").withCompany("test5").withAddress("test6")
                    .withHomepage("test7").withGroup("test group"), true);
        }
    }

    @Test(enabled = true)
    public void testContactDeletion() throws InterruptedException {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.withOut(deletedContact)));
    }
}
