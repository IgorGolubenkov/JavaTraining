package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;

public class TestCreationContact extends TestBase{

    @Test
    public void testCreationContact() {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().thereIsAGroupForChoice()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().goToAddandEditContactPage();
        app.getContactHelper().fillContactForm(new ContactData("test1", "test2", "test3",
                "test4", "test5", "test6", "test7", "test8", "test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
        }
}
