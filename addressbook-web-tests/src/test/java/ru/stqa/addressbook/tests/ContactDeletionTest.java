package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase{

    @Test(enabled = false)
    public void testContactDeletion() {
        app.goTo().goToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("test1", "test2", "test3",
                    "test4", "test5", "test6", "test7", "test8", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().deleteSelectedContact(before.size() - 1);
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().confirmationContactDeletion();
        app.goTo().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
