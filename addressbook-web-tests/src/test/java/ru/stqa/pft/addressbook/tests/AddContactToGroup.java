package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author a.kovtun
 * @since 07.05.2018.
 */
public class AddContactToGroup extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.getNavigationHelper().gotoHome();
            app.getContactHelper().createContact(new ContactData().withFirstname("name"), true);
        }
        if (app.db().groups().size() == 0) {
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData().withName("group"));
        }

        Groups groups = app.db().groups();
        if (app.getContactHelper().findContactNotInGroup(app.db().contacts(), groups)==null) {
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData().withName("NEW"));
        }
    }

    @Test
    public void testAddContactToGroup() {
        Groups groups = app.db().groups();
        ContactData addContact = app.getContactHelper().findContactNotInGroup(app.db().contacts(), groups);
        GroupData toGroup = app.getContactHelper().chooseGroup(addContact, groups);
        Set<GroupData> groupsBefore = app.db().contactInGroups(addContact);
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().addContactToGroup(addContact, toGroup);
        Set<GroupData> groupsAfter = app.db().contactInGroups(addContact);
        groupsBefore.add(toGroup);
        assertThat(groupsAfter, equalTo(groupsBefore));
    }


}

