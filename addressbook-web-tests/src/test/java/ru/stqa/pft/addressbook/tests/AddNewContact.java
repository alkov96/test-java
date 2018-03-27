package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * @author a.kovtun
 * @since 26.03.2018.
 */
public class AddNewContact extends TestBase {
    @Test
    public void testAddNewContact() {
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().createContact(new ContactData("la","dada", "ul", "gtg"));
        app.getNavigationHelper().gotoHome();
    }
}
