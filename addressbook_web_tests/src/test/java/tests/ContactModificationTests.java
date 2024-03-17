package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    public void canModifyContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("", "Пётр", "Иванов", "Г.Москва, ул. Мира 6, кв.25", "ivanov24@gmail.com", "+79262545574", "src/test/resources/images/avatar.png"));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName("modified name").withLastName("modified surname");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canAddContactToGroup() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("", "Пётр", "Иванов", "Г.Москва, ул. Мира 6, кв.25", "ivanov24@gmail.com", "+79262545574", "src/test/resources/images/avatar.png"));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            app.refreshPage();
        }
        var oldContacts = app.hbm().getContactList();
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var contactIndex = rnd.nextInt(oldContacts.size());
        var groupIndex = rnd.nextInt(oldGroups.size());
        var contact = oldContacts.get(contactIndex);
        var group = oldGroups.get(groupIndex);
        var oldRelated = app.hbm().getContactsInGroup(group);
        if (oldRelated.contains(contact)) {
            oldRelated.remove(oldRelated.indexOf(contact));
        }
        app.contacts().addContactToGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertFalse(oldRelated.contains(contact));
        Assertions.assertTrue(newRelated.contains(contact));
    }

    @Test
    public void canRemoveContactFromGroup() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("", "Пётр", "Иванов", "Г.Москва, ул. Мира 6, кв.25", "ivanov24@gmail.com", "+79262545574", "src/test/resources/images/avatar.png"));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            app.refreshPage();
        }
        var oldContacts = app.hbm().getContactList();
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var contactIndex = rnd.nextInt(oldContacts.size());
        var groupIndex = rnd.nextInt(oldGroups.size());
        var contact = oldContacts.get(contactIndex);
        var group = oldGroups.get(groupIndex);
        app.contacts().addContactToGroup(contact, group);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().removeContactFromGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertFalse(newRelated.contains(contact));
        Assertions.assertTrue(oldRelated.contains(contact));
    }



}
