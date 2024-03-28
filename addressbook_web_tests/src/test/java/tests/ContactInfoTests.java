package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testContactInfo() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "Пётр", "Иванов", "Г.Москва, ул. Мира 6, кв.25", "ivanov24@gmail.com", "", "","src/test/resources/images/avatar.png", "", "+79262545574", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var phones = app.contacts().getPhones();
        var address = app.contacts().getAddress();
        var emails = app.contacts().getEmails();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && !s.equals(""))
                        .collect(Collectors.joining("\n"))
        ));
        Assertions.assertEquals(expected, phones);
        expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null && !s.equals(""))
                        .collect(Collectors.joining("\n"))
        ));
        Assertions.assertEquals(expected, address);
        expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && !s.equals(""))
                        .collect(Collectors.joining("\n"))
        ));
        Assertions.assertEquals(expected, emails);
    }


}
