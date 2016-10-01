package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(app, new ContactData("test1", "test2", "test3",
                    "test4", "test5", "test6", "test7", "test8", "test1"), true);
        }
        app.getContactHelper().modificationSelectedContact();
        app.getContactHelper().fillContactForm(new ContactData("edit test1", "edit test2",
                "edit test3", "edit test4", "edit test5", "edit test6", "edit test7", "edit test8", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
