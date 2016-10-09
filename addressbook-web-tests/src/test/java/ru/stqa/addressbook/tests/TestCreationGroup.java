package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.Set;

public class TestCreationGroup extends TestBase {

    @Test
    public void testCreationGroup() {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();
        //int before = app.group().getGroupCount();
        GroupData group = new GroupData().withName("test254");
        app.group().create(group);
        //int after = app.group().getGroupCount();
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        //int max = 0;
        //for (GroupData g : after) {
        //    if (g.getId() > max) {
        //        max = g.getId();
        //    }
        //}
        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
    }
}
