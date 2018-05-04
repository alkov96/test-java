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
public class DeleteContact extends TestBase {
   @Test
    public void testDeleteContact() {
        if (app.db().contacts().size() == 0) {
            app.getNavigationHelper().gotoHome();
            app.getContactHelper().createContact(new ContactData().withFirstname("name"), true);
        }
       Contacts before = app.db().contacts();
       ContactData deletedContact = before.iterator().next();
       app.getNavigationHelper().gotoHome();
        app.getContactHelper().deleteSelectedId(deletedContact);
        app.getContactHelper().deleteContact();
       app.getContactHelper().acceptAlert();
        app.getNavigationHelper().gotoHome();
       Contacts after = app.db().contacts();
       assertEquals(after.size(), before.size()-1 );
       assertThat(after, equalTo(before.without(deletedContact)));
       }

   }

