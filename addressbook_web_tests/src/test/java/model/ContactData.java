package model;

public record ContactData(String firstname, String lastname, String address, String email, String phone) {

    public ContactData() {
        this("","","","","");
    }

}
