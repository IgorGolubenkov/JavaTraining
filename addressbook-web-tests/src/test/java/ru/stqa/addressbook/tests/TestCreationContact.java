package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

public class TestCreationContact extends TestBase{

    @Test
    public void testCreationContact() throws InterruptedException {
        app.getNavigationHelper().goToAddandEditContactPage();
        app.getContactHelper().fillGroupForm(new ContactData("test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8"));
        app.getContactHelper().submitContactCreation();
        Thread.sleep(3000);
        }
}
