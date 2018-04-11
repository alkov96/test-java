package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @author a.kovtun
 * @since 26.03.2018.
 */
public class DeleteContact extends TestBase {
   @Test(enabled = false)
    public void testDeleteContact() {
       app.getNavigationHelper().gotoHome();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("la", "dada", "ul", "gtg"));
        }
       List<ContactData> before = app.getContactHelper().getContactList();
       app.getNavigationHelper().gotoHome();
        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().gotoHome();
       List<ContactData> after = app.getContactHelper().getContactList();
       Assert.assertEquals(after.size(), before.size() -1 );

       before.remove(before.size()-1);
           Assert.assertEquals(before, after);
       }

   }

