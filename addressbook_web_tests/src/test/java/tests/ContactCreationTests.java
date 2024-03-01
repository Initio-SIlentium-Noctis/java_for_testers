package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData("Пётр", "Иванов", "Г.Москва, ул. Мира 6, кв.25", "ivanov24@gmail.com", "+79262545574"));
    }

    @Test
    public void canCreateEmptyContact() {
        app.contacts().createContact(new ContactData("","","","",""));
    }

    @Test
    public void canCreateContactWithFirstNameOnly() {
        app.contacts().createContact(new ContactData("Сергей","","","",""));
    }

    @Test
    public void canCreateContactWithLastNameOnly() {
        app.contacts().createContact(new ContactData("","Иванов","","",""));
    }

    @Test
    public void canCreateContactWithEmailOnly() {
        app.contacts().createContact(new ContactData("","","","petrov123@gmail.com",""));
    }

    @Test
    public void canCreateContactWithPhoneOnly() {
        app.contacts().createContact(new ContactData("","","","","+79262545574"));
    }


}
