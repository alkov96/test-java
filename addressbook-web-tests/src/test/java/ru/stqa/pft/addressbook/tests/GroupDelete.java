package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDelete extends TestBase {

    

    
    @Test
    public void testGroupDelete() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }


}


