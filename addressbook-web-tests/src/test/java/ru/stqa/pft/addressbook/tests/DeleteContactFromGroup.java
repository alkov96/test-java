package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author a.kovtun
 * @since 07.05.2018.
 */
public class DeleteContactFromGroup extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData().withName("name"));
        }
        if (app.db().contacts().size() == 0) {
            app.getNavigationHelper().gotoHome();
            app.getContactHelper().createContact(new ContactData().withFirstname("name").withLastname("last"), true);
        }

        ContactData contact = app.getContactHelper().findContactInGroup(app.db().contacts());
        if (contact == null) {
            contact = app.db().contacts().iterator().next();
            app.getContactHelper().addContactToGroup(contact, app.db().groups().iterator().next());
        }
    }

    @Test
    public void testDeleteContactFromGroup(){
        Contacts contacts = app.db().contacts();
        ContactData contact = app.getContactHelper().findContactInGroup(contacts);
        GroupData deletedGroup = app.db().contactInGroups(contact).iterator().next();
        app.getNavigationHelper().gotoHome();
        Set<GroupData> before= contact.getGroups();
        app.getContactHelper().deleteContactFromGroup(contact, deletedGroup);
        Set<GroupData> after = app.db().contactInGroups(contact);
        before.remove(deletedGroup);
        assertThat(after, equalTo(before));
    }
}
