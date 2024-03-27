package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "Пётр", "Иванов", "Г.Москва, ул. Мира 6, кв.25", "ivanov24@gmail.com", "", "","src/test/resources/images/avatar.png", "", "+79262545574", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
            Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                    .filter(s -> s != null && !s.equals(""))
                    .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testAddress() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "Пётр", "Иванов", "Г.Москва, ул. Мира 6, кв.25", "ivanov24@gmail.com", "", "","src/test/resources/images/avatar.png", "", "+79262545574", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null && !s.equals(""))
                        .collect(Collectors.joining("\n"))
        ));
        var address = app.contacts().getAddress();
        Assertions.assertEquals(expected, address);
    }

    @Test
    void testEmails() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "Пётр", "Иванов", "Г.Москва, ул. Мира 6, кв.25", "ivanov24@gmail.com", "", "", "src/test/resources/images/avatar.png", "", "+79262545574", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && !s.equals(""))
                        .collect(Collectors.joining("\n"))
        ));
        var emails = app.contacts().getEmails();
        Assertions.assertEquals(expected, emails);
    }


}
