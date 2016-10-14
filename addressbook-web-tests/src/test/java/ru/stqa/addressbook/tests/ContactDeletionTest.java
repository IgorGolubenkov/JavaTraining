package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase{

    @Test(enabled = false)
    public void testContactDeletion() {
        app.goTo().goToHomePage();
        if (! app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstname("test1").withMiddlename("test2").withLastname("test3").withNickname("test4")
                    .withTitle("test4").withCompany("test5").withAddress("test6").withHomepage("test7"));
        }
        List<ContactData> before = app.contact().getContactList();
        app.contact().deleteSelectedContact(before.size() - 1);
        app.contact().submitContactDeletion();
        app.contact().confirmationContactDeletion();
        app.goTo().goToHomePage();
        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
