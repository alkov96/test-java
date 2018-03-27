package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * @author a.kovtun
 * @since 26.03.2018.
 */
public class ContactModification extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().selectContact();
        app.getContactHelper().fillContactForm(new ContactData("la","dada", "ul", null),false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHome();

    }
}
