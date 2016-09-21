package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase{

    @Test
    public void testContactDeletion() {
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().confirmationContactDeletion();
        app.getNavigationHelper().goToHomePage();
    }
}
