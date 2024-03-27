package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactsPage() {
        if (!manager.isElementPresent(By.linkText("add new"))) {
            click(By.id("logo"));
        }
    }

    public boolean isContactPresent() {
        openContactsPage();
        if (!manager.isElementPresent(By.name("selected[]"))) {
            return false;
        } else return true;
    }

    public void createContact(ContactData contact) {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactsPage();
    }

    public void createContactInGroup(ContactData contact, GroupData group) {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToContactsPage();
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        openContactsPage();
        selectContact(contact);
        addToGroup(group);
        submitContactAddToGroup();
        returnToContactsPage();
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        openContactsPage();
        sortContactsByGroup(group);
        selectContact(contact);
        submitRemoveContactFromGroup();
        returnToContactsPage();
    }

    private void submitRemoveContactFromGroup() {
        click(By.name("remove"));
    }

    private void sortContactsByGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactsPage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToContactsPage();
    }

    public void removeContact(ContactData contact) {
        openContactsPage();
        selectContact(contact);
        removeSelectedContact();
        returnToContactsPage();
    }

    public void removeAllContacts() {
        openContactsPage();
        selectAllContacts();
        removeSelectedContacts();
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        attach(By.name("photo"), contact.photo());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("mobile"), contact.mobile());
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public int getCount() {
        openContactsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void submitContactAddToGroup() {
        click(By.name("add"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void removeSelectedContacts() {
        click(By.cssSelector("input[value='Delete']"));
    }

    private void removeSelectedContact() {
        click(By.cssSelector("input[value='Delete']"));
    }

    private void returnToContactsPage() {
        click(By.id("logo"));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contact) {
        click(By.xpath(String.format("//input[@id='%s']/../..//img[@title='Edit']", contact.id())));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[id='%s']", contact.id())));
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void addToGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    public List<ContactData> getList() {
        openContactsPage();
        var contacts = new ArrayList<ContactData>();
        var entries = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var entry : entries) {
            var id = entry.findElement(By.name("selected[]")).getAttribute("id");
            var firstname = entry.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var lastname = entry.findElement(By.cssSelector("td:nth-child(2)")).getText();
            contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return contacts;
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id = '%s']/../../td[6]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getAddress() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var address = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, address);
        }
        return result;
    }

    public Map<String, String> getEmails() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var emails = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, emails);
        }
        return result;
    }


}
