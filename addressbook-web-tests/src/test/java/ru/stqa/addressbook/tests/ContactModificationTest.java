package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        app.goTo().goToHomePage();
        if (! app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstname("test1").withMiddlename("test2").withLastname("test3")
                    .withNickname("test4").withTitle("test4").withCompany("test5").withAddress("test6")
                    .withHomepage("test7").withGroup("test group"), true);
        }
    }

    @Test(enabled = true)
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifyConctacts = before.iterator().next();
        ContactData contact = new ContactData().withId(modifyConctacts.getId()).withFirstname("edit test1").withMiddlename("edit test2")
                .withLastname("edit test3").withNickname("edit test4").withTitle("edit test5").withCompany("edit test6").withAddress("edit test7")
                .withHomepage("edit test8");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        //Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        //before.sort(byId);
        //after.sort(byId);
        assertThat(after, equalTo(before.withOut(modifyConctacts).withAdded(contact)));
    }
}
