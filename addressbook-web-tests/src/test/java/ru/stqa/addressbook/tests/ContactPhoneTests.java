package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        app.goTo().goToHomePage();
        if (! app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstname("test1").withLastname("test3").withGroup("test group")
                    .withHomePhone("+79182738").withMobilePhone("+7913453638").withWorkPhone("+723498942"), false);
        }
    }

    @Test
    public void testContactPhones() throws InterruptedException {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditFrom = app.contact().infoFromEditFromPhone(contact);

        assertThat(contact.getAllPhone(), equalTo(mergePhones(contactInfoFromEditFrom)));
        //assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditFrom.getHomePhone())));
        //assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditFrom.getMobilePhone())));
        //assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditFrom.getWorkPhone())));
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .filter((s -> ! s.equals(""))).map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
