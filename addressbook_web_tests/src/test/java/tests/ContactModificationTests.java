package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void canModifyContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("Пётр", "Иванов", "Г.Москва, ул. Мира 6, кв.25", "ivanov24@gmail.com", "+79262545574"));
        }
        app.contacts().modifyContact(new ContactData("Сергей", "", "", "", ""));
    }
}
