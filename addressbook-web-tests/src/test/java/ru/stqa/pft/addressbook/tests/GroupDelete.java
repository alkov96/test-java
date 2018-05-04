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
        if (app.db().contacts().size() == 0) {
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData().withName("group"));
        }
    }

    @Test
    public void testGroupDelete() {
       Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().deleteSelectedId(deletedGroup);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        Groups after = app.db().groups();
        assertThat(app.getGroupHelper().count(),equalTo(before.size()-1));
        assertThat(after, equalTo(before.without(deletedGroup)));
        }
    }





