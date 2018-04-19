package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * @author a.kovtun
 * @since 26.03.2018.
 */
public class AddNewContact extends TestBase {
    @Test
    public void testAddNewContact() {
       Contacts before = app.getContactHelper().all();
        ContactData contact = new ContactData().withFirstname("NAME").withAllPhones("123").withGroup("gf");
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().gotoHome();
        assertThat(app.getContactHelper().count(),equalTo(before.size()+1));
        Contacts after = app.getContactHelper().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadAddNewContact() {
        Contacts before = app.getContactHelper().all();
        ContactData contact = new ContactData().withFirstname("NAME'''").withAllPhones("bvn").withGroup("gf");
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().gotoHome();
        assertThat(app.getContactHelper().count(),equalTo(before.size()));
        Contacts after = app.getContactHelper().all();
        assertThat(after, equalTo(before));
    }
}
