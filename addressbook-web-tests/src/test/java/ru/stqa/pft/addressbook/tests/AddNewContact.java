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
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("la","dada", "ul"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHome();
    }
}
