package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class TestCreationContact extends TestBase{

    @Test(enabled = false)
    public void testCreationContact() {
        app.goTo().goToHomePage();
        if (! app.getContactHelper().thereIsAGroupForChoice()) {
            app.getContactHelper().initCreationGroup(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.goTo().goToAddandEditContactPage();
        ContactData contact = new ContactData("test1", "test2", "test3",
                "test4", "test5", "test6", "test7", "test8", "test254");
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContactCreation();
        app.goTo().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);



        }
}
