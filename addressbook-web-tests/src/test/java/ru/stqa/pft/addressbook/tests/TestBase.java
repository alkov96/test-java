package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

import static java.lang.System.getProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author a.kovtun
 * @since 22.03.2018.
 */
public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static ApplicationManager app;

    static {
        try {
            app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    FirefoxDriver wd;

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method m) {
        logger.info("Запуск теста " + m.getName());


    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Остановка теста " + m.getName());

    }
    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")){
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.getGroupHelper().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g)-> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }

    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")){
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.getContactHelper().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((g)-> new ContactData().withFirstname(g.getFirstname()).withLastname(g.getLastname()).withAddress(g.getAddress()))
                    .collect(Collectors.toSet())));
        }

    }
}
