package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

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

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("home"), contactData.getAddress());
        type(By.name("address2"), contactData.getHouse());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void selectContact(int index) {
        wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@class]/td[@class='center']//img[@title='Details']")).get(index).click();
    }

    public void clickContactModification() {
        click(By.xpath("//div[@id='content']//form[@action='edit.php']/input[2]"));
    }
    public void submitContactModification() {
        click(By.name("update"));
    }


    public void deleteContact() {
        click(By.xpath("//div[@id='content']//form[@action='edit.php']/input[2]"));
        click(By.cssSelector("input[value='Delete']"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(new ContactData("la", "dada", "ul", "gtg"), true);
        submitContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']//tr[@name='entry']"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath("td[3]")).getText();
            String address = element.findElement(By.xpath("td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData(id, firstname, address, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}

