package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

/**
 * @author a.kovtun
 * @since 26.03.2018.
 */

@XStreamAlias("contact")
public class ContactData {
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    private  String firstname;
    private  String address;
    private  String group;
    private String lastname;
    private String email;
    private String email2;
    private String work;
    private String email3;
    private String home;
    private String mobile;
    private String AllEmails;
    private String phones;

    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    private File photo;

    public String getAllEmails() {
        return AllEmails;
    }

    public ContactData withAllEmails(String phones) {
        this.AllEmails = phones;
        return this;
    }

    public String getAllPhones() {
        return phones;
    }

    public int getId() {
        return id;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withAllPhones(String phones) {
        this.phones = phones;
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
    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }
    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }
    public ContactData withHome(String home) {
        this.home = home;
        return this;
    }
    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
    public ContactData withWork(String work) {
        this.work = work;
        return this;
    }



    public String getFirstname() {
        return firstname;
    }

    public String getPhones() {
        return phones;
    }

    public String getAddress()  {
        return address;
    }

    public String getLastname()  {
        return lastname;
    }
    public String getEmail()  {
        return email;
    }
    public String getEmail2()  {
        return email2;
    }
    public String getEmail3()  {
        return email3;
    }
    public String getWork()  {
        return work;
    }
    public String getHome()  {
        return home;
    }
    public String getMobile()  {
        return mobile;
    }


    public String getGroup()  {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", phones='" + phones + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return phones != null ? phones.equals(that.phones) : that.phones == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (phones != null ? phones.hashCode() : 0);
        return result;
    }
}


