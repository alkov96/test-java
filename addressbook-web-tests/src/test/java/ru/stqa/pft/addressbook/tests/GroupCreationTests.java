package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {
    @BeforeSuite
    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData>before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("gtg", "ff", "dg");
        app.getGroupHelper().createGroup(group);
        List<GroupData>after = app.getGroupHelper().getGroupList();
        Assert.assertEquals( after.size(), before.size() +1);


        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

    }
}

