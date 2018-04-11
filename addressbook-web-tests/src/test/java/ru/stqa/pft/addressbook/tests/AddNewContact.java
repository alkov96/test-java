package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * @author a.kovtun
 * @since 26.03.2018.
 */
public class AddNewContact extends TestBase {
    @Test(enabled = false)
    public void testAddNewContact() {
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("la","ul", "dada", "gtg");
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().gotoHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (a1, a2) -> Integer.compare(a1.getId(), a2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
