package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author a.kovtun
 * @since 19.04.2018.
 */
public class ContactInfoTests extends TestBase {
    @Test
    public void testContactInfo() {
        app.getNavigationHelper().gotoHome();
        ContactData contact = app.getContactHelper().all().iterator().next();
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        System.out.println("Почта совпадает.");
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        System.out.println("Телефоны совпадают.");
        assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
        System.out.println("Адрес совпадает.");
    }

    private String mergeAddress(ContactData contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter(s -> !s.equals("")).collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
                .stream().filter(s -> !s.equals("")).
                        map(ContactInfoTests::cleaned).
                        collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter(s -> !s.equals("")).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
       return phone.replaceAll("\\s","").replaceAll("-", "");
    }
}
