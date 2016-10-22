package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        app.goTo().goToHomePage();
        if (! app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withAddress("   country Russia city Moscow, Lenina street apartment 1 apartment 1"), false);
        }
    }

    @Test
    public void testContactAddress() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditFrom = app.contact().infoFromEditFrom(contact);
        assertThat(contact.getAddress(), equalTo(cleaned(contactInfoFromEditFrom.getAddress())));
    }

    public static String cleaned(String address) {
        return address.replaceAll("^[\\s]*", "");
    }

}

