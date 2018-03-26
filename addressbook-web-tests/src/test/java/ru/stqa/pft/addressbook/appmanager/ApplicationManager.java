package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

/**
 * @author a.kovtun
 * @since 22.03.2018.
 */
public class ApplicationManager {
    FirefoxDriver wd;

private SessionHelper sessionHelper;
    private  NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;


    public void init() {
<<<<<<< HEAD
        if (browser == BrowserType.FIREFOX) {
            wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("C:\\Users\\a.kovtun\\AppData\\Local\\Mozilla Firefox\\firefox.exe"));
        }
        else if (browser == BrowserType.CHROME) {
            wd = new ChromeDriver();
        }
        else if (browser == BrowserType.IE) {
            wd = new InternetExplorerDriver();
        }
=======
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("C:\\Users\\a.kovtun\\AppData\\Local\\Mozilla Firefox\\firefox.exe"));
>>>>>>> parent of 061d6b4... Browser
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }



    public void stop() {
       wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
