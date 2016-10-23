package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    public void testContactEmails() throws InterruptedException {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditFrom = app.contact().infoFromEditFrom(contact);
        assertThat(contact.getEmail(), equalTo(cleaned(contactInfoFromEditFrom.getEmail())));
        assertThat(contact.getEmail2(), equalTo(cleaned(contactInfoFromEditFrom.getEmail2())));
        assertThat(contact.getEmail3(), equalTo(cleaned(contactInfoFromEditFrom.getEmail3())));
        assertThat(mergeEmailMasterPage(contact), equalTo(mergeEmailEditForm(contactInfoFromEditFrom)));
    }

    private String mergeEmailEditForm(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .filter((s -> ! s.equals(""))).map(ContactEmailTests::cleaned).collect(Collectors.joining("\n"));
    }
    private String mergeEmailMasterPage(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .filter((s -> ! s.equals(""))).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String email) {
        return email.replaceAll("(^\\s*)|(\\s*$)", "");
    }
}
