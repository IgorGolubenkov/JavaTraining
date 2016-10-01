package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase{

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(app, new ContactData("test1", "test2", "test3",
                    "test4", "test5", "test6", "test7", "test8", "test1"), true);
        }
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().confirmationContactDeletion();
        app.getNavigationHelper().goToHomePage();
    }
}
