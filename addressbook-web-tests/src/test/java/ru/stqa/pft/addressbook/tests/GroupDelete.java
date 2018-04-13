package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDelete extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData().withName("gf"));
        }
    }

    @Test
    public void testGroupDelete() {
       Groups before = app.getGroupHelper().all();
        GroupData deletedGroup = before.iterator().next();
        app.getGroupHelper().deleteSelectedId(deletedGroup);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        Groups after = app.getGroupHelper().all();
        assertEquals( after.size(), before.size()-1);
        assertThat(after, equalTo(before.without(deletedGroup)));
        }
    }





