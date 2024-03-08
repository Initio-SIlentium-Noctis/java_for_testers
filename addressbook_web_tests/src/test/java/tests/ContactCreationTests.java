package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (int i = 0; i < 10; i++) {
            result.add(new ContactData()
                    .withId("")
                    .withFirstName(randomFirstName())
                    .withLastName(randomLastName())
                    .withAddress(randomAddress())
                    .withEmail(randomEmail())
                    .withPhone(randomPhone()));
        }
        return result;
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(new ContactData ("", "Сергей'", "", "", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact
                .withId(newContacts.get(newContacts.size() - 1).id())
                .withFirstName(newContacts.get(newContacts.size() - 1).firstname())
                .withLastName(newContacts.get(newContacts.size() - 1).lastname()));
        expectedList.sort(compareById);
        for (int i = 0 ; i < newContacts.size(); i++) {
            Assertions.assertEquals(newContacts.get(i).firstname(), expectedList.get(i).firstname());
            Assertions.assertEquals(newContacts.get(i).lastname(), expectedList.get(i).lastname());
        }
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
        app.contacts().createContact(new ContactData("","","","","",""));
    }

    @Test
    public void canCreateContactWithFirstNameOnly() {
        app.contacts().createContact(new ContactData("","Сергей","","","",""));
    }

    @Test
    public void canCreateContactWithLastNameOnly() {
        app.contacts().createContact(new ContactData("","","Иванов","","",""));
    }

    @Test
    public void canCreateContactWithEmailOnly() {
        app.contacts().createContact(new ContactData("","","","","petrov123@gmail.com",""));
    }

    @Test
    public void canCreateContactWithPhoneOnly() {
        app.contacts().createContact(new ContactData("","","","","","+79262545574"));
    }


}
