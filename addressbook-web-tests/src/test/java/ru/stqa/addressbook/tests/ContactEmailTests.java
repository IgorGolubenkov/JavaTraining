package ru.stqa.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.addressbook.tests.ContactPhoneTests.cleaned;

public class ContactEmailTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        app.goTo().goToHomePage();
        if (! app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstname("test1").withLastname("test3").withEmail("test@test.test")
                    .withEmail2("tes2t@test2.test2").withEmail3("test3@test3.test3"), false);
        }
    }

    @Test
    public void testContactPhones() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditFrom = app.contact().infoFromEditFrom(contact);
        assertThat(contact.getEmail(), equalTo(contactInfoFromEditFrom.getEmail()));
        assertThat(contact.getEmail2(), equalTo(contactInfoFromEditFrom.getEmail2()));
        assertThat(contact.getEmail3(), equalTo(contactInfoFromEditFrom.getEmail3()));
    }
}
