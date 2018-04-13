package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        ContactData contact = new ContactData().withFirstname("NAME").withHouse("bvn").withAddress("vf").withGroup("gf");
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().gotoHome();
        Contacts after = app.getContactHelper().all();
        assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
    }
}
