package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupDelete extends TestBase {

    

    
    @Test
    public void testGroupDelete() {
        gotoGroupPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }


}


