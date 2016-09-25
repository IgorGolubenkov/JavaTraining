package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification() {
        app.getContactHelper().modificationSelectedContact();
        app.getContactHelper().fillContactForm(new ContactData("edit test1", "edit test2",
                "edit test3", "edit test4", "edit test5", "edit test6", "edit test7", "edit test8", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
