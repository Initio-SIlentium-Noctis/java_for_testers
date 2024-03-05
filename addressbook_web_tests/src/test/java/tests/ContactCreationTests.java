package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (int i = 0; i < 10; i++) {
            result.add(new ContactData(randomFirstName(), randomLastName(), randomAddress(), randomEmail(), randomPhone()));
        }
        return result;
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(new ContactData ("Сергей'", "", "", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount, newContactCount);
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
