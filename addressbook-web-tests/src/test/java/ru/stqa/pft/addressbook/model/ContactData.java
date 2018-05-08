package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author a.kovtun
 * @since 26.03.2018.
 */

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Column(name = "firstname")
    private  String firstname;

    @Column(name = "address")
    @Type(type = "text")
    private  String address;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Column(name = "work")
    @Type(type = "text")
    private String work;

    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Column(name = "home")
    @Type(type = "text")
    private String home;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;

    @Transient
    private String AllEmails;

    @Transient
    private String phones;


    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id")
            , inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }


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

    public Groups getGroups() {
        return new Groups(groups);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", groups=" + groups +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return groups != null ? groups.equals(that.groups) : that.groups == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }
}


