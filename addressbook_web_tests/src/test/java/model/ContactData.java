package model;

public record ContactData(String firstname, String lastname, String address, String email, String phone) {

    public ContactData() {
        this("","","","","");
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(firstname, this.lastname, this.address, this.email, this.phone);
    }

}
