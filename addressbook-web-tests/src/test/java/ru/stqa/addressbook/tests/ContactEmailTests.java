package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        ContactData contactInfoFromEditFrom = app.contact().infoFromEditFromAddress(contact);
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).filter((s -> ! s.equals(""))).map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }


}
