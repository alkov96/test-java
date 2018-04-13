package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
       app.getNavigationHelper().gotoHome();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData().withFirstname("bgd").withAddress("gvdf").withHouse("bdf"), true);
        }
       Contacts before = app.getContactHelper().all();
       ContactData deletedContact = before.iterator().next();
       app.getNavigationHelper().gotoHome();
        app.getContactHelper().deleteSelectedId(deletedContact);
        app.getContactHelper().deleteContact();
       app.getContactHelper().acceptAlert();
        app.getNavigationHelper().gotoHome();
       Contacts after = app.getContactHelper().all();
       assertEquals(after.size(), before.size()-1 );
       assertThat(after, equalTo(before.without(deletedContact)));
       }

   }

