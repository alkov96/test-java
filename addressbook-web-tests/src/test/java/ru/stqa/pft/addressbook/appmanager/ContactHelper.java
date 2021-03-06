package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a.kovtun
 * @since 26.03.2018.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean creationWithGroup) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("lastname"), contactData.getLastname());
        //attach(By.name("photo"), contactData.getPhoto());


        if (creationWithGroup) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactCreation() {
        click(By.name("submit"));
        contactCache = null;

    }

    public void selectContact(int index) {
        wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@class]/td[@class='center']//img[@title='Details']")).get(index).click();
    }

    public void clickContactModification() {
        click(By.xpath("//div[@id='content']//form[@action='edit.php']/input[2]"));
    }

    public void submitContactModification() {
        click(By.name("update"));
        contactCache = null;
    }


    public void deleteContact() {
        //click(By.xpath("//div[@id='content']//form[@action='edit.php']/input[2]"));
        click(By.cssSelector("input[value='Delete']"));
        contactCache = null;
    }

    public void Alert() {
        acceptAlert();
    }

    public void createContact(ContactData contact, boolean creationWithGroup) {
        initContactCreation();
        fillContactForm(contact, creationWithGroup);
        submitContactCreation();

    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']//tr[@name='entry']"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath("td[3]")).getText();
            String lastname = element.findElement(By.xpath("td[2]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            String phones = element.findElement(By.xpath("td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactData().withFirstname(firstname).withAddress(address).withLastname(lastname));
        }
        return contacts;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']//tr[@name='entry']"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath("td[3]")).getText();
            String phones = element.findElement(By.xpath("td[6]")).getText();
            String Allemails = element.findElement(By.xpath("td[5]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withAllPhones(phones).withAddress(address).
                    withAllEmails(Allemails));
        }
        return contactCache;
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedId(ContactData contact) {
        selectContactById(contact.getId());
    }

    //public void modifySelectedId(ContactData contact) {
    //  selectContactById(contact.getId());
    //}
    public void selectModify(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }

    public void modifySelectedId(ContactData contact) {
        selectModify(contact.getId());
    }

    public ContactData infoFromEditForm(ContactData contact) {
        selectModify(contact.getId());
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withAddress(address).withFirstname(firstname).
                withLastname(lastname).withEmail(email).withEmail2(email2).withEmail3(email3).withHome(home).withMobile(mobile).withWork(work);
    }

    public ContactData findContactNotInGroup(Contacts contacts, Groups groups) {
        ContactData contactNotInGroup = null;
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() != groups.size()) {
                contactNotInGroup = contact;
                break;
            }

        }
        return contactNotInGroup;
    }

    public GroupData chooseGroup(ContactData addContact, Groups groups) {
        groups.removeAll(addContact.getGroups());
        return groups.iterator().next();
    }

    public void addContactToGroup(ContactData addContact, GroupData toGroup) {
        selectContactById(addContact.getId());
        click(By.xpath("//select[@name='to_group']"));
        click(By.xpath("//select[@name='to_group']/option[@value='" + toGroup.getId() + "']"));
        click(By.xpath("//input[@type='submit']"));
    }

    public void deleteContactFromGroup(ContactData contact, GroupData deletedGroup) {
        click(By.xpath("//form[@id='right']"));
        click(By.xpath("//form[@id='right']/select[@name='group']/option[@value='" + deletedGroup.getId() + "']"));
        selectContactById(contact.getId());
        click(By.xpath("//input[@name='remove']"));
    }

    public ContactData findContactInGroup(Contacts contacts) {
        ContactData contactInGroup = null;
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() != 0) {
                contactInGroup = contact;
                break;
            }
        }
        return contactInGroup;

    }
}




