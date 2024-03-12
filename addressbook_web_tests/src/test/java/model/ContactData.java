package model;

public record ContactData(String id, String firstname, String lastname, String address, String email, String phone, String photo) {

    public ContactData() {
        this("","","","","","", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.lastname, this.address, this.email, this.phone, this.photo);
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.address, this.email, this.phone, this.photo);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.address, this.email, this.phone, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, address, this.email, this.phone, this.photo);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, email, this.phone, this.photo);
    }

    public ContactData withPhone(String phone) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, phone, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, this.phone, photo);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContactData contact = (ContactData) o;
        return this.firstname.equals(contact.firstname) && this.lastname.equals(contact.lastname);
    }

}
