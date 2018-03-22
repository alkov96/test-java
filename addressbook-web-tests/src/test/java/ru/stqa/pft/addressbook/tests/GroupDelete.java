package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDelete extends TestBase {

    

    
    @Test
    public void testGroupDelete() {
        app.gotoGroupPage();
        selectGroup();
        app.deleteSelectedGroups();
        app.returnToGroupPage();
    }


}


