package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.HelperBase;

/**
 * @author a.kovtun
 * @since 26.03.2018.
 */
public class DeleteContact extends TestBase {
    @Test
    public void testDeleteContact() {
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().gotoHome();
    }
}
