package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "Пётр", "Иванов", "Г.Москва, ул. Мира 6, кв.25", "ivanov24@gmail.com", "+79262545574", "src/test/resources/images/avatar.png"));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canRemoveAllContactsAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "Пётр", "Иванов", "Г.Москва, ул. Мира 6, кв.25", "ivanov24@gmail.com", "+79262545574", "src/test/resources/images/avatar.png"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.hbm().getContactList().size());
    }
}
