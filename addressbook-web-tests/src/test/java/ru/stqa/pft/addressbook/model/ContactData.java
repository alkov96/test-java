package ru.stqa.pft.addressbook.model;

/**
 * @author a.kovtun
 * @since 26.03.2018.
 */
public class ContactData {
    private int id = Integer.MAX_VALUE;
    private  String firstname;
    private  String house;
    private  String address;
    private  String group;



    public int getId() {
        return id;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withHouse(String house) {
        this.house = house;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;

    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
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

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", house='" + house + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return house != null ? house.equals(that.house) : that.house == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        return result;
    }
}


