package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() { });
        result.addAll(value);
        return result;
    }

    public static List<ContactData> singleRandomContact(){
        return List.of(new ContactData()
                .withId("")
                .withFirstName(CommonFunctions.randomFirstName())
                .withLastName(CommonFunctions.randomLastName())
                .withAddress(CommonFunctions.randomAddress())
                .withEmail(CommonFunctions.randomEmail())
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"))
                .withMobilePhone(CommonFunctions.randomPhone()));
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(new ContactData().withFirstName("Сергей'")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
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
        Assertions.assertEquals(newContacts, expectedList);
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
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
        Assertions.assertEquals(newContacts, expectedList);
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContactInGroup(ContactData contact) {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContactInGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Assertions.assertEquals(oldContacts.size(), newContacts.size());
    }

    @Test
    public void canCreateEmptyContact() {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithFirstNameOnly() {
        app.contacts().createContact(new ContactData().withFirstName("Сергей"));
    }

    @Test
    public void canCreateContactWithLastNameOnly() {
        app.contacts().createContact(new ContactData().withLastName("Иванов"));
    }

    @Test
    public void canCreateContactWithEmailOnly() {
        app.contacts().createContact(new ContactData().withEmail("petrov123@gmail.com"));
    }

    @Test
    public void canCreateContactWithMobilePhoneOnly() {
        app.contacts().createContact(new ContactData().withMobilePhone("+79262545574"));
    }


}
