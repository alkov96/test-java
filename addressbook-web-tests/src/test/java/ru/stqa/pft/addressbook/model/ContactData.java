package ru.stqa.pft.addressbook.model;

/**
 * @author a.kovtun
 * @since 26.03.2018.
 */
public class ContactData {
    private final String firstname;
    private final String house;
    private final String address;

    public ContactData(String firstname, String house, String address) {
        this.firstname = firstname;
        this.house = house;
        this.address = address;
}
    public String getFirstname() {
        return firstname;
    }

    public String getHouse() {
        return house;
    }

    public String getAddress() {
        return address;
    }
}
