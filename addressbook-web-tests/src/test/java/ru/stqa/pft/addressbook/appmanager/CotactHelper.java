package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver; /**
 * @author a.kovtun
 * @since 22.03.2018.
 */
public class CotactHelper extends HelperBase {


    public CotactHelper(FirefoxDriver wd) {

        super(wd);
    }
    public void login(String username, String password) {
        type(By.name("user"), username);
        type(By.name("pass"), password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
