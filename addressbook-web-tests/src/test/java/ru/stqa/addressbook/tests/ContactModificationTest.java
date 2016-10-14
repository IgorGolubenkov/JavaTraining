package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase{

    @Test(enabled = false)
    public void testContactModification() {
        app.goTo().goToHomePage();
        if (! app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstname("test1").withMiddlename("test2").withLastname("test3").withNickname("test4")
                    .withTitle("test4").withCompany("test5").withAddress("test6").withHomepage("test7"));
        }
        List<ContactData> before = app.contact().getContactList();
        app.contact().modificationSelectedContact(before.size() - 1);
        ContactData contact = new ContactData().withId(before.get(before.size() - 1).getId()).withFirstname("edit test1").withMiddlename("edit test2")
                .withLastname("edit test3").withNickname("edit test4").withTitle("edit test5").withCompany("edit test6").withAddress("edit test7")
                .withHomepage("edit test8");
        app.contact().fillContactForm(contact, false);
        app.contact().submitContactModification();
        app.goTo().goToHomePage();
        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
