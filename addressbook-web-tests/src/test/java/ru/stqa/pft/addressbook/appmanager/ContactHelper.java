package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

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
    public void selectContact () {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }
    public void submitContactModification () {
       click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }
    public void deleteContact (){
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }
    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(new ContactData("la","dada", "ul", "gtg"), true );
        submitContactCreation();
    }
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}

