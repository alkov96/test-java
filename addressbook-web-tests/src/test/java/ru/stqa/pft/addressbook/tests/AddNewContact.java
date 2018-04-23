package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * @author a.kovtun
 * @since 26.03.2018.
 */
public class AddNewContact extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String xml = "";
        String line =  reader.readLine();
        while (line != null) {
            xml += line;
            line =  reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>)xstream.fromXML(xml);
        return  contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testAddNewContact() {
       Contacts before = app.getContactHelper().all();
        File photo = new File("src/test/resources/cat.jpg");
        ContactData contact = new ContactData().withFirstname("NAME").withAllPhones("123").withPhoto(photo).withGroup("gf");
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().gotoHome();
        assertThat(app.getContactHelper().count(),equalTo(before.size()+1));
        Contacts after = app.getContactHelper().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadAddNewContact() {
        Contacts before = app.getContactHelper().all();
        File photo = new File("src/test/resources/cat.jpg");
        ContactData contact = new ContactData().withFirstname("NAME'''").withAllPhones("bvn").withPhoto(photo);
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().gotoHome();
        assertThat(app.getContactHelper().count(),equalTo(before.size()));
        Contacts after = app.getContactHelper().all();
        assertThat(after, equalTo(before));
    }

}
