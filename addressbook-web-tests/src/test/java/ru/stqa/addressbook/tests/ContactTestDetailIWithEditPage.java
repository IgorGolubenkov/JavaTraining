package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTestDetailIWithEditPage extends TestBase{

    @Test
    public void testContactEmails() throws InterruptedException {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromDetailPage = app.contact().infoFromDetailPage(contact);
        ContactData contactInfoFromEditFrom = app.contact().infoFromEditFrom(contact);
        assertThat(mergeInfoFromDetailPage(contactInfoFromDetailPage), equalTo(mergeInfoFromEditFrom(contactInfoFromEditFrom)));
    }

    private String mergeInfoFromEditFrom(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .filter((s -> ! s.equals(""))).map(ContactEmailTests::cleaned).collect(Collectors.joining("\n"));
    }
    private String mergeInfoFromDetailPage(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .filter((s -> ! s.equals(""))).collect(Collectors.joining("\n"));
    }
}
