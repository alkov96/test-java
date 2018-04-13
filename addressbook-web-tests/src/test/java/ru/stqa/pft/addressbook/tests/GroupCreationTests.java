package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;



public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        Groups before = app.getGroupHelper().all();
        GroupData group = new GroupData().withName("gnhgjngt").withHeader("bgf").withFooter("gdf");
        app.getGroupHelper().createGroup(group);
        Groups after = app.getGroupHelper().all();
        assertThat(after.size(),equalTo(before.size()+1));
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));

    }
}

