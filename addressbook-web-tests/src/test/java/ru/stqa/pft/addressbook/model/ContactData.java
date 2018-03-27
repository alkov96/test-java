package ru.stqa.pft.addressbook.model;

/**
 * @author a.kovtun
 * @since 26.03.2018.
 */
public class ContactData {
    private final String firstname;
    private final String house;
    private final String address;
    private final String group;

    public ContactData(String firstname, String house, String address, String group) {
        this.firstname = firstname;
        this.house = house;
        this.address = address;
        this.group = group;
    }
    public String getFirstname() {
        return firstname;
    }

    public String getHouse() {
        return house;
    }

    public String getAddress()  {
        return address;
    }


    public String getGroup()  {
        return group;
    }
    }

