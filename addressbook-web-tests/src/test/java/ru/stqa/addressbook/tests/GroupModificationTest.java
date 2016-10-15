package ru.stqa.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.group().all();
        GroupData modifyGroup = before.iterator().next();
        GroupData group = new GroupData().withName("edit test1").withHeader("edit test2")
                .withFooter("edit test3").withId(modifyGroup.getId());
        app.group().modify(group);
        Groups after = app.group().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(modifyGroup).withAdded(group)));
    }

}
