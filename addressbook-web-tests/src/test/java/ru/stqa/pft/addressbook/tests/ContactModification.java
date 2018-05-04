package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * @author a.kovtun
 * @since 26.03.2018.
 */
public class ContactModification extends TestBase {
    @Test
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.getNavigationHelper().gotoHome();
            app.getContactHelper().createContact(new ContactData().withFirstname("name"), true);
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/cat.jpg");
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withAddress("address").withFirstname("name").withLastname("LAST").withPhoto(photo)/*withAllPhones("1234")*/;
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().modifySelectedId(contact);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHome();
        assertThat(app.getContactHelper().count(),equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }
}
