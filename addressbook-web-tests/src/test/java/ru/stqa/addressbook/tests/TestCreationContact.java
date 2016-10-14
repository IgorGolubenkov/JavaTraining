package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreationContact extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().goToHomePage();
        if (! app.contact().thereIsAGroupForChoice()) {
            app.contact().initCreationGroup(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    public void testCreationContact() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("test1").withMiddlename("test2").withLastname("test3").withNickname("test4")
                .withTitle("test4").withCompany("test5").withAddress("test6").withHomepage("test7").withGroup("test254");
        app.contact().createContact(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        //contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        }


}
